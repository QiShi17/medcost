package com.realdd.medcost.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.realdd.medcost.common.api.CommonPage;
import com.realdd.medcost.common.api.CommonResult;
import com.realdd.medcost.dto.AccountInfoBrief;
import com.realdd.medcost.entity.ExpenseAccount;
import com.realdd.medcost.service.AccountInfoBriefSevice;
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
    @Autowired
    AccountInfoBriefSevice accountInfoBriefSevice;

    @ApiOperation("添加报销单")
    @PostMapping(value = "/create")
    public CommonResult create(@RequestBody ExpenseAccount expenseAccount){
        boolean success=expenseAccountService.create(expenseAccount);
        if(success){
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }

    @ApiOperation("根据id获取报销单信息（单表查询）")
    @GetMapping(value = "/pure/{id}")
    public CommonResult getPureById(@PathVariable Long id){
        return CommonResult.success(expenseAccountService.getById(id));
    }

    @ApiOperation("分页获取报销单列表信息（多表查询）")
    @GetMapping(value = "/list")
    public CommonResult list(@RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize,
                             @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
        Page<AccountInfoBrief> accountInfoBriefPage=accountInfoBriefSevice.getAccountInfoBriefPage(pageSize,pageNum);
        return CommonResult.success(CommonPage.restPage(accountInfoBriefPage));
    }

}
