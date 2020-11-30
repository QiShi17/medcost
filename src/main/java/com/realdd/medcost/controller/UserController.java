package com.realdd.medcost.controller;


import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.realdd.medcost.common.api.CommonPage;
import com.realdd.medcost.common.api.CommonResult;
import com.realdd.medcost.common.exception.ApiException;
import com.realdd.medcost.common.utils.FileUtil;
import com.realdd.medcost.dto.UserLoginParam;
import com.realdd.medcost.entity.Role;
import com.realdd.medcost.entity.User;
import com.realdd.medcost.service.OssService;
import com.realdd.medcost.service.RoleService;
import com.realdd.medcost.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author cute_duanduan
 * @since 2020-10-23
 */
@Api(tags = "UserController", description = "后台用户管理")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private OssService ossService;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    public CommonResult<User> register(@RequestBody User userParam, BindingResult result) {
        User user = userService.register(userParam);
        if (user == null) {
            CommonResult.failed();
        }
        return CommonResult.success(user);
    }

    @ApiOperation(value = "登录以后返回token")
    @PostMapping(value = "/login")
    public CommonResult login(@RequestBody UserLoginParam userLoginParam, BindingResult result) {
        String token = userService.login(userLoginParam.getUsername(), userLoginParam.getPassword());
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    @ApiOperation(value = "获取当前登录用户信息")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getAdminInfo(Principal principal) {
        if(principal==null){
            return CommonResult.unauthorized(null);
        }
        String username = principal.getName();
        User user = userService.getUserByUsername(username);
        Map<String, Object> data = new HashMap<>();
        data.put("username", user.getUsername());
        data.put("realname",user.getRealname());
        List<Role> roleList = roleService.getRoleListByUserId(user.getId());
        if(CollUtil.isNotEmpty(roleList)){
            List<String> roles = roleList.stream().map(Role::getValue).collect(Collectors.toList());
            data.put("roles",roles);
        }
        data.put("id",user.getId());
        data.put("gender",user.getGender());
        data.put("age",user.getAge());
        data.put("type",user.getType());
        data.put("school",user.getSchool());
        data.put("major",user.getMajor());
        data.put("grade",user.getGrade());
        data.put("department",user.getDepartment());
        data.put("annualExpense",user.getAnnualExpense());
        return CommonResult.success(data);
    }

//    @ApiOperation(value = "根据用户id获取用户信息")
//    @RequestMapping(value = "/info", method = RequestMethod.GET)
//    @ResponseBody
//    public CommonResult getAdminInfo(Principal principal) {
//        if(principal==null){
//            return CommonResult.unauthorized(null);
//        }
//        String username = principal.getName();
//        User user = userService.getUserByUsername(username);
//        Map<String, Object> data = new HashMap<>();
//        data.put("username", user.getUsername());
//        data.put("realname",user.getRealname());
//        List<Role> roleList = roleService.getRoleListByUserId(user.getId());
//        if(CollUtil.isNotEmpty(roleList)){
//            List<String> roles = roleList.stream().map(Role::getValue).collect(Collectors.toList());
//            data.put("roles",roles);
//        }
//        return CommonResult.success(data);
//    }

    @ApiOperation(value = "登出功能")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult logout() {
        return CommonResult.success(null);
    }

    @ApiOperation("获取用户所有角色")
    @GetMapping(value = "/role/{userId}")
    @PreAuthorize("hasRole('USER')")
    public CommonResult<List<Role>> getRoleList(@PathVariable Long userId) {
        List<Role> roleList = roleService.getRoleListByUserId(userId);
        return CommonResult.success(roleList);
    }

    @ApiOperation("给用户分配角色")
    @PostMapping(value = "/role/update")
    @ResponseBody
    public CommonResult updateRole(@RequestParam("userId") Long userId,
                                   @RequestParam("roleIds") List<Long> roleIds) {
        int count = userService.updateRole(userId, roleIds);
        if (count >= 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("根据用户名或姓名分页获取用户列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<User>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                               @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                               @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page<User> userList = userService.list(keyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(userList));
    }

    @ApiOperation("修改指定用户信息")
    @PostMapping(value = "/update/{id}")
    public CommonResult update(@PathVariable Long id, @RequestBody User user) {
        boolean success = userService.update(id, user);
        if (success) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "导出清单", notes = "export", produces = "application/octet-stream")
    @GetMapping("/test")
    public void export(HttpServletResponse response){

        List<User> userList=userService.list();


        //导出操作
        FileUtil.exportExcel(userList,"用户表","用户",User.class,"用户.xls",response);
    }
//
//    @GetMapping("importExcel")
//    public void importExcel() {
//        String filePath = "D:\\海贼王.xls";
//        //解析excel，
//        List<Person> personList = FileUtil.importExcel(filePath, 1, 1, Person.class);
//        //也可以使用MultipartFile,使用 FileUtil.importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass)导入
//        System.out.println("导入数据一共【" + personList.size() + "】行");
//    }

    @ApiOperation(value = "导入清单", notes = "import", produces = "application/octet-stream")
    @PostMapping("/importExcel2")
    public CommonResult<Object> importExcel2(@RequestParam("file") MultipartFile file) {
        Boolean success=userService.insertUserByExcel(file);
        if(success) return CommonResult.success(null);
        else return  CommonResult.failed("导入文件失败");
    }

    @ApiOperation(value = "导入清单", notes = "import", produces = "application/octet-stream")
    @PostMapping("/import_excel")
    public CommonResult<Object> importExcel(@RequestParam("filename") String filename) {
        Boolean success=ossService.getUserExcel(filename);
        if(success) return CommonResult.success(null);
        else return  CommonResult.failed("导入文件失败");
    }


    }
