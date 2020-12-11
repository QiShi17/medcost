package com.realdd.medcost.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.realdd.medcost.common.api.CommonPage;
import com.realdd.medcost.common.api.CommonResult;
import com.realdd.medcost.common.utils.UserInfoUtil;
import com.realdd.medcost.common.value.AccountStatus;
import com.realdd.medcost.dto.AccountDetail;
import com.realdd.medcost.dto.AccountInfoBrief;
import com.realdd.medcost.entity.ExpenseAccount;
import com.realdd.medcost.entity.User;
import com.realdd.medcost.service.AccountInfoBriefService;
import com.realdd.medcost.service.ExpenseAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

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
    AccountInfoBriefService accountInfoBriefService;
    @Autowired
    UserInfoUtil userInfoUtil;

    @ApiOperation("添加报销单")
    @PostMapping(value = "/create")
    public CommonResult create(@RequestBody ExpenseAccount expenseAccount) {
        boolean success = expenseAccountService.create(expenseAccount);
        if (success) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }

    @ApiOperation("根据id获取报销单信息（单表查询）")
    @GetMapping(value = "/pure/{id}")
    public CommonResult getPureById(@PathVariable Long id) {
        return CommonResult.success(expenseAccountService.getById(id));
    }

    @ApiOperation("分页获取报销单列表信息（多表查询）")
    @GetMapping(value = "/list")
    public CommonResult list(@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                             @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page<AccountInfoBrief> accountInfoBriefPage = accountInfoBriefService.getAccountInfoBriefPage(pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(accountInfoBriefPage));
    }

    @ApiOperation("分页获取我审核的单据")
    @GetMapping(value = "/my_review/list")
    public CommonResult myReviewList(@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                     @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                     @RequestParam(value = "status") Integer status,
                                     Principal principal) {

        if(principal==null){
            return CommonResult.unauthorized(null);
        }
        User user=userInfoUtil.getUserInfo(principal);
        String username=null;
        if(user!=null){
            username=user.getUsername();
        }
        //如果不是审核中，要看全部的单据（除了自己）
        Page<AccountInfoBrief> accountInfoBriefPage=null;
        if(status!=null && status.equals(AccountStatus.STATUS_REVIEW)){
            accountInfoBriefPage = accountInfoBriefService.getAccountInfoBriefPageReview(pageSize,pageNum,null,status,username);
        }else{
            accountInfoBriefPage=accountInfoBriefService.getAccountInfoBriefPageReview(pageSize,pageNum,username,status,username);
        }
        return CommonResult.success(CommonPage.restPage(accountInfoBriefPage));
    }

    @ApiOperation("分页获取我提交的单据")
    @GetMapping(value = "/my_submit/list")
    public CommonResult mySubmitList(@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                     @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                     @RequestParam(value = "status") Integer status,
                                     Principal principal) {
        if(principal==null){
            return CommonResult.unauthorized(null);
        }
        User user=userInfoUtil.getUserInfo(principal);
        String username=null;
        if(user!=null){
            username=user.getUsername();
        }
        //查看自己的对应状态的单据
        Page<AccountInfoBrief> accountInfoBriefPage=accountInfoBriefService.getAccountInfoBriefPageSubmit(pageSize,pageNum,username,status);
        return CommonResult.success(CommonPage.restPage(accountInfoBriefPage));
    }

    @ApiOperation("分页获取我复查的单据")
    @GetMapping(value = "/my_master/list")
    public CommonResult myMasterList(@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                     @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                     @RequestParam(value = "status") Integer status) {
        //查看自己的对应状态的单据
        Page<AccountInfoBrief> accountInfoBriefPage=accountInfoBriefService.getAccountInfoBriefPageSubmit(pageSize,pageNum,null,status);
        return CommonResult.success(CommonPage.restPage(accountInfoBriefPage));
    }

    @ApiOperation("分页获取我收单的单据")
    @GetMapping(value = "/my_deliver/list")
    public CommonResult myDeliverList(@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                     @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                     @RequestParam(value = "status") Integer status) {
        //查看自己的对应状态的单据
        Page<AccountInfoBrief> accountInfoBriefPage=accountInfoBriefService.getAccountInfoBriefPageSubmit(pageSize,pageNum,null,status);
        return CommonResult.success(CommonPage.restPage(accountInfoBriefPage));
    }

    @ApiOperation("获取详情页")
    @GetMapping(value = "/detail/{id}")
    public CommonResult<List<AccountDetail>> fetchAccountDetail(@PathVariable Long id) {
        List<AccountDetail> accountDetailsList = expenseAccountService.getAccountDetailByExpenseAccountId((long) id);
        System.out.println(accountDetailsList);
        return CommonResult.success(accountDetailsList);
    }


    @ApiOperation(value = "确认收单")
    @PostMapping("/deliver/{id}")
    public CommonResult deliver(@PathVariable Long id){
        boolean success = expenseAccountService.deliverExpenseAccount(id);
        if (success) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();

    }

}
