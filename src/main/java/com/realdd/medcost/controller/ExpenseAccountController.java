package com.realdd.medcost.controller;


import com.realdd.medcost.common.api.CommonResult;
import com.realdd.medcost.entity.ExpenseAccount;
import com.realdd.medcost.service.ExpenseAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 报销单 前端控制器
 * </p>
 *
 * @author cute_duanduan
 * @since 2020-11-08
 */
@Api(tags = "ExpenseAccountController", description = "报销单管理")
@RestController
@RequestMapping("/expense_account")
public class ExpenseAccountController {

    @Autowired
    ExpenseAccountService expenseAccountService;

    @ApiOperation("添加报销单")
    @PostMapping(value = "/create")
    public CommonResult create(@RequestBody ExpenseAccount expenseAccount){
        boolean success=expenseAccountService.create(expenseAccount);
        if(success){
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }

}
