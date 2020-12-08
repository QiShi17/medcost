package com.realdd.medcost.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.realdd.medcost.common.api.CommonPage;
import com.realdd.medcost.common.api.CommonResult;
import com.realdd.medcost.entity.Department;
import com.realdd.medcost.service.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 部门表 前端控制器
 * </p>
 *
 * @author huangxiaohou
 * @since 2020-11-12
 */
@Api(tags = "DepartmentController", description = "部门管理")
@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @ApiOperation(value = "添加部门")
    @PostMapping("/create")
    public CommonResult createDepartment(@RequestBody Department department){

        boolean success = departmentService.createDepartment(department);
        if (success) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();

    }

    @ApiOperation(value = "修改部门")
    @PostMapping("/update/{id}")
    public CommonResult updateDepartment(@PathVariable Long id, @RequestBody Department department){

        boolean success = departmentService.updateDepartment(id, department);
        if (success) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();

    }

    @ApiOperation(value = "删除部门")
    @PostMapping("/delete/{id}")
    public CommonResult deleteDepartment(@PathVariable Long id){

        boolean success = departmentService.deleteDepartment(id);
        if (success) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();

    }

    @ApiOperation("根据用户名或姓名分页获取部门列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<Department>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                               @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                               @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page<Department> list = departmentService.list(keyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(list));
    }



}
