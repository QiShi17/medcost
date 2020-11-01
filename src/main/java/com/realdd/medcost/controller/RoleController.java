package com.realdd.medcost.controller;


import com.realdd.medcost.common.api.CommonResult;
import com.realdd.medcost.entity.Role;
import com.realdd.medcost.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 用户角色表 前端控制器
 * </p>
 *
 * @author cute_duanduan
 * @since 2020-10-23
 */
@Api(tags = "UmsRoleController", description = "后台用户角色管理")
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ApiOperation("获取所有角色")
    @GetMapping(value = "/listAll")
    @PreAuthorize("hasRole('ADMIN')")
    public CommonResult<List<Role>> listAll() {
        List<Role> roleList = roleService.list();
        return CommonResult.success(roleList);
    }




}
