package com.realdd.medcost.service;

import com.realdd.medcost.entity.ExpenseAccount;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
