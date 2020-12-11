package com.realdd.medcost.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.realdd.medcost.common.utils.SerialNumberUtil;
import com.realdd.medcost.dto.AccountDetail;
import com.realdd.medcost.entity.ExpenseAccount;
import com.realdd.medcost.entity.ReviewerExpenseAccountRelation;
import com.realdd.medcost.entity.Role;
import com.realdd.medcost.entity.User;
import com.realdd.medcost.mapper.*;
import com.realdd.medcost.service.ExpenseAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 报销单 服务实现类
 * </p>
 *
 * @author cute_duanduan
 * @since 2020-11-08
 */
@Service
public class ExpenseAccountServiceImpl extends ServiceImpl<ExpenseAccountMapper, ExpenseAccount> implements ExpenseAccountService {
    @Autowired
    SerialNumberUtil serialNumberUtil;

    @Autowired
    AccountDetailMapper accountDetailMapper;

    @Autowired
    ExpenseAccountMapper expenseAccountMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public boolean create(ExpenseAccount expenseAccount) {

        expenseAccount.setSerialNum(serialNumberUtil.getSerialNumber());
        return save(expenseAccount);
    }

    @Override
    public List<AccountDetail> getAccountDetailByExpenseAccountId(Long expenseAccountId) {
        return this.accountDetailMapper.getAccountDetailByExpenseAccountId(expenseAccountId);
    }

    @Override
    public boolean deliverExpenseAccount(Long expenseAccountId) {

        ExpenseAccount expenseAccount = getById(expenseAccountId);
        if (3 == expenseAccount.getStatus()) {
            expenseAccount.setStatus(5);
            Double expenseAccountTotal = expenseAccountMapper.selectExpenseAccountTotal(expenseAccountId);
            Double annualExpense = expenseAccountMapper.selectAnnualExpense(expenseAccountId);
            User user = expenseAccountMapper.selectUser(expenseAccountId);
            Double totalExpense = expenseAccountTotal + annualExpense;
            user.setAnnualExpense(totalExpense);
            userMapper.updateById(user);
            expenseAccountMapper.updateById(expenseAccount);
            return true;
        } else {
            return false;
        }
    }


}
