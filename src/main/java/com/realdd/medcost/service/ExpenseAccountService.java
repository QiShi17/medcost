package com.realdd.medcost.service;

import com.realdd.medcost.dto.AccountDetail;
import com.realdd.medcost.entity.Department;
import com.realdd.medcost.entity.ExpenseAccount;
import com.baomidou.mybatisplus.extension.service.IService;
import com.realdd.medcost.entity.Role;

import java.util.List;

/**
 * <p>
 * 报销单 服务类
 * </p>
 *
 * @author cute_duanduan
 * @since 2020-11-08
 */
public interface ExpenseAccountService extends IService<ExpenseAccount> {
    /**
     * 添加报销单
     */
    boolean create(ExpenseAccount expenseAccount);

    /**
     * 获取ExpenseAccount的详情页
     */
    AccountDetail getAccountDetailByExpenseAccountId(Long expenseAccountId);

    /**
     * 确认收单
     * @param expenseAccountId
     * @return
     */
    boolean deliverExpenseAccount(Long expenseAccountId);

     * 审核负责人将该id的单据撤销(改变状态为7)
     */
    boolean agreeExpenseAccountById(Long expenseAccountId,String reviewerUsername);

    boolean rejectExpenseAccountById(Long expenseAccountId,String reviewerUsername,String comment);

    boolean revokeExpenseAccountById(Long expenseAccountId,String masterUsername);
}
