package com.realdd.medcost.controller;


import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.realdd.medcost.common.api.CommonPage;
import com.realdd.medcost.common.api.CommonResult;
import com.realdd.medcost.dto.UserLoginParam;
import com.realdd.medcost.entity.Role;
import com.realdd.medcost.entity.User;
import com.realdd.medcost.service.RoleService;
import com.realdd.medcost.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    //    @GetMapping("/test")
//    public CommonResult test(){
//        return CommonResult.success(new User(),tokenHeader);
//    }
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
        return CommonResult.success(data);
    }

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


}
