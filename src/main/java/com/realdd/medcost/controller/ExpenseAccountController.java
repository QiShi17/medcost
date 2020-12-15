package com.realdd.medcost.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.realdd.medcost.common.api.CommonPage;
import com.realdd.medcost.common.api.CommonResult;
import com.realdd.medcost.common.utils.UserInfoUtil;
import com.realdd.medcost.common.value.AccountStatus;
import com.realdd.medcost.dto.Account2Print;
import com.realdd.medcost.dto.AccountDetail;
import com.realdd.medcost.dto.AccountInfoBrief;
//import com.realdd.medcost.dto.AccountResultStatistic;
import com.realdd.medcost.dto.AccountResultStatistic;
import com.realdd.medcost.entity.ExpenseAccount;
import com.realdd.medcost.entity.User;
import com.realdd.medcost.service.AccountInfoBriefService;
//import com.realdd.medcost.service.AccountResultStatisticService;
import com.realdd.medcost.service.AccountResultStatisticService;
import com.realdd.medcost.service.ExpenseAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.print.DocFlavor;
import java.security.Principal;
import java.util.ArrayList;
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
    AccountResultStatisticService accountResultStatisticService;
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
    public CommonResult list(@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page<AccountInfoBrief> accountInfoBriefPage = accountInfoBriefService.getAccountInfoBriefPage(pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(accountInfoBriefPage));
    }

    @ApiOperation("分页获取我审核的单据")
    @GetMapping(value = "/my_review/list")
    public CommonResult myReviewList(@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "status") Integer status, Principal principal) {

        if (principal == null) {
            return CommonResult.unauthorized(null);
        }
        User user = userInfoUtil.getUserInfo(principal);
        String username = null;
        if (user != null) {
            username = user.getUsername();
        }
        //如果不是审核中，要看全部的单据（除了自己）
        Page<AccountInfoBrief> accountInfoBriefPage = null;
        if (status != null && status.equals(AccountStatus.STATUS_REVIEW)) {
            accountInfoBriefPage = accountInfoBriefService.getAccountInfoBriefPageReview(pageSize, pageNum, null, status, username);
        } else {
            accountInfoBriefPage = accountInfoBriefService.getAccountInfoBriefPageReview(pageSize, pageNum, username, status, username);
        }
        return CommonResult.success(CommonPage.restPage(accountInfoBriefPage));
    }

    @ApiOperation("分页获取我提交的单据")
    @GetMapping(value = "/my_submit/list")
    public CommonResult mySubmitList(@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "status") Integer status, Principal principal) {
        if (principal == null) {
            return CommonResult.unauthorized(null);
        }
        User user = userInfoUtil.getUserInfo(principal);
        String username = null;
        if (user != null) {
            username = user.getUsername();
        }
        //查看自己的对应状态的单据
        Page<AccountInfoBrief> accountInfoBriefPage = accountInfoBriefService.getAccountInfoBriefPageSubmit(pageSize, pageNum, username, status);
        return CommonResult.success(CommonPage.restPage(accountInfoBriefPage));
    }

    @ApiOperation("分页获取我复查的单据")
    @GetMapping(value = "/my_master/list")
    public CommonResult myMasterList(@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "status") Integer status) {
        //查看自己的对应状态的单据
        Page<AccountInfoBrief> accountInfoBriefPage = accountInfoBriefService.getAccountInfoBriefPageSubmit(pageSize, pageNum, null, status);
        return CommonResult.success(CommonPage.restPage(accountInfoBriefPage));
    }

    @ApiOperation("分页获取我收单的单据")
    @GetMapping(value = "/my_deliver/list")
    public CommonResult myDeliverList(@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "status") Integer status) {
        //查看自己的对应状态的单据
        Page<AccountInfoBrief> accountInfoBriefPage = accountInfoBriefService.getAccountInfoBriefPageSubmit(pageSize, pageNum, null, status);
        return CommonResult.success(CommonPage.restPage(accountInfoBriefPage));
    }

    @ApiOperation("获取详情页")
    @GetMapping(value = "/detail/{id}")
    public CommonResult<AccountDetail> fetchAccountDetail(@PathVariable Long id) {
        System.out.println("进入1");
        AccountDetail accountDetail = expenseAccountService.getAccountDetailByExpenseAccountId((long) id);
        System.out.println(accountDetail);
        return CommonResult.success(accountDetail);
    }

    @ApiOperation("获取审核中详情页")
    @GetMapping(value = "/in_review_detail/{id}")
    public CommonResult<AccountDetail> fetchInReviewAccountDetail(@PathVariable Long id) {
        System.out.println("进入2");
        AccountDetail accountDetail = expenseAccountService.getInReviewAccountDetailByExpenseAccountId((long) id);
        System.out.println(accountDetail);
        return CommonResult.success(accountDetail);
    }

    @ApiOperation(value = "审核员同意单据")
    @PostMapping("/agree/{expenseAccountId}")
    public CommonResult agreeExpenseAccountById(@PathVariable Long expenseAccountId, @RequestParam("reviewerUsername") String reviewerUsername) {
        System.out.println("进入同意");
        boolean success = expenseAccountService.agreeExpenseAccountById(expenseAccountId, reviewerUsername);
        System.out.println(success);
        if (success) return CommonResult.success(null);
        return CommonResult.failed();
    }

    @ApiOperation(value = "审核员拒绝单据")
    @PostMapping("/reject/{expenseAccountId}")
    public CommonResult rejectExpenseAccountById(@PathVariable Long expenseAccountId, @RequestParam("reviewerUsername") String reviewerUsername, @RequestParam("comment") String comment) {
        System.out.println("进入拒绝");
        boolean success = expenseAccountService.rejectExpenseAccountById(expenseAccountId, reviewerUsername, comment);
        System.out.println(success);
        if (success) return CommonResult.success(null);
        return CommonResult.failed();
    }

    @ApiOperation(value = "负责人撤销同意的单据")
    @PostMapping("/revoke/{expenseAccountId}")
    public CommonResult revokeExpenseAccountById(@PathVariable Long expenseAccountId, @RequestParam("masterUsername") String masterUsername) {
        System.out.println("进入撤销");
        boolean success = expenseAccountService.revokeExpenseAccountById(expenseAccountId, masterUsername);
        System.out.println(success);
        if (success) return CommonResult.success(null);
        return CommonResult.failed();
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

    @ApiOperation("打印单据")
    @GetMapping(value = "/print/{id}")
    public CommonResult<Account2Print> fetchAccount2print(@PathVariable Long id) {
        System.out.println("进入打印");
        Account2Print account2Print = expenseAccountService.getAccount2PrintByExpenseAccountId((Long)id);
        System.out.println(account2Print);
        return CommonResult.success(account2Print);
    }

    @ApiOperation("统计各学院/部门的人数、报销金额")
    @GetMapping(value = "/statistic")
    public CommonResult<AccountResultStatistic> fetchAccountResultStatistic() {
        System.out.println("进入统计结果");
        AccountResultStatistic accountResultStatistic=new AccountResultStatistic();
        List<String> departmentList=accountResultStatisticService.getAllDepartmentAndSchool();//找到所有部门和学院
        List<User> userList=accountResultStatisticService.getAllUser();//找到所有用户
        System.out.println(userList);
        List<Integer>departmentAndSchooolNum = new ArrayList<Integer>();
        for(int i=0;i<departmentList.size();i++) departmentAndSchooolNum.add(0);
        for(int i=0;i<departmentList.size();i++)
            for(int j=0;j<userList.size();j++)
                if(departmentList.get(i).equals(userList.get(j).getDepartment())||departmentList.get(i).equals(userList.get(j).getSchool()))
                    departmentAndSchooolNum.set(i,departmentAndSchooolNum.get(i)+1);
        accountResultStatistic.setDepartmentAndSchoolList(departmentList);
        accountResultStatistic.setDepartmentAndSchoolNumList(departmentAndSchooolNum);
        System.out.println(accountResultStatistic);
        return CommonResult.success(accountResultStatistic);
    }
}
