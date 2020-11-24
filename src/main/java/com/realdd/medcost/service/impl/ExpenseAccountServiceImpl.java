package com.realdd.medcost.service.impl;

import com.realdd.medcost.common.utils.SerialNumberUtil;
import com.realdd.medcost.dto.AccountDetail;
import com.realdd.medcost.entity.ExpenseAccount;
import com.realdd.medcost.entity.Role;
import com.realdd.medcost.mapper.AccountDetailMapper;
import com.realdd.medcost.mapper.ExpenseAccountMapper;
import com.realdd.medcost.mapper.RoleMapper;
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

    @Override
    public boolean create(ExpenseAccount expenseAccount) {

        expenseAccount.setSerialNum(serialNumberUtil.getSerialNumber());
        return save(expenseAccount);
    }

    @Override
    public List<AccountDetail> getAccountDetailByExpenseAccountId(Long expenseAccountId) {
        return this.accountDetailMapper.getAccountDetailByExpenseAccountId(expenseAccountId);
    }

}
