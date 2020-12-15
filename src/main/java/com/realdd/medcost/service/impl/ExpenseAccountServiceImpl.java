package com.realdd.medcost.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.realdd.medcost.common.utils.SerialNumberUtil;
import com.realdd.medcost.dto.Account2Print;
import com.realdd.medcost.dto.AccountDetail;
import com.realdd.medcost.entity.ExpenseAccount;
import com.realdd.medcost.entity.ReviewerExpenseAccountRelation;
import com.realdd.medcost.entity.Role;
import com.realdd.medcost.entity.User;
import com.realdd.medcost.mapper.*;
import com.realdd.medcost.mapper.AccountDetailMapper;
import com.realdd.medcost.mapper.ExpenseAccountMapper;
import com.realdd.medcost.mapper.ReviewerExpenseAccountRelationMapper;
import com.realdd.medcost.mapper.RoleMapper;
import com.realdd.medcost.service.ExpenseAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
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
    Account2PrintMapper account2PrintMapper;

    @Autowired
    UserMapper userMapper;
    
    @Autowired
    ReviewerExpenseAccountRelationMapper reviewerExpenseAccountRelationMapper;

    @Override
    public boolean create(ExpenseAccount expenseAccount) {

        expenseAccount.setSerialNum(serialNumberUtil.getSerialNumber());
        return save(expenseAccount);
    }

    @Override
    public AccountDetail getAccountDetailByExpenseAccountId(Long expenseAccountId) {
        return this.accountDetailMapper.getAccountDetailByExpenseAccountId(expenseAccountId);
    }

    @Override
    public AccountDetail getInReviewAccountDetailByExpenseAccountId(Long expenseAccountId) {
        return this.accountDetailMapper.getInReviewAccountDetailByExpenseAccountId(expenseAccountId);
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

    @Override
    public boolean agreeExpenseAccountById(Long expenseAccountId,String reviewerUsername) {
        System.out.println("名字是"+reviewerUsername);
        ExpenseAccount expenseAccount = getById(expenseAccountId);
        System.out.println("单据是"+expenseAccount);
        ReviewerExpenseAccountRelation reviewerExpenseAccountRelation=new ReviewerExpenseAccountRelation();
        reviewerExpenseAccountRelation.setExpenseAccountId(expenseAccountId);
        reviewerExpenseAccountRelation.setCreateTime(new Date());
        reviewerExpenseAccountRelation.setUpdateTime(reviewerExpenseAccountRelation.getCreateTime());
        reviewerExpenseAccountRelation.setReviewerIdNum(reviewerUsername);
        Integer expenseType=expenseAccount.getExpenseTypeId();
        Double originalFee=expenseAccount.getInvoiceFee()+expenseAccount.getRegistFee();
        Double total=0.0;
        switch (expenseType)
        {
            case 1:
            case 2:
            case 5:
                if(originalFee<=1300)
                    total=originalFee*0.8;
                else
                    total=originalFee*0.9;
                break;
            case 3:
                if(originalFee<=1300)
                    total=originalFee*0.9;
                else
                    total=originalFee;
                break;
            case 4:
                total=originalFee;
                break;
            default:
                total=originalFee;
        }
        reviewerExpenseAccountRelation.setTotal(total);
        reviewerExpenseAccountRelation.setStatus(1);        //审核通过
        reviewerExpenseAccountRelation.setIsCancel(0);      //审核负责人没有取消申请通过的状态
        //不设置comment,因为直接审核通过，没有修改意见
        System.out.println("relation是"+reviewerExpenseAccountRelation);
        if(expenseAccount.getStatus()==2)
        {
            expenseAccount.setStatus(3);
            return saveOrUpdate(expenseAccount) && reviewerExpenseAccountRelationMapper.add(reviewerExpenseAccountRelation);
        }
        return false;
    }

    @Override
    public boolean rejectExpenseAccountById(Long expenseAccountId, String reviewerUsername, String comment) {
        ExpenseAccount expenseAccount = getById(expenseAccountId);
        ReviewerExpenseAccountRelation reviewerExpenseAccountRelation=new ReviewerExpenseAccountRelation();
        reviewerExpenseAccountRelation.setExpenseAccountId(expenseAccountId);
        reviewerExpenseAccountRelation.setCreateTime(new Date());
        reviewerExpenseAccountRelation.setUpdateTime(reviewerExpenseAccountRelation.getCreateTime());
        reviewerExpenseAccountRelation.setReviewerIdNum(reviewerUsername);
        reviewerExpenseAccountRelation.setComment(comment);
        reviewerExpenseAccountRelation.setStatus(0);//审核不通过
        //不设置is_cancel,因为没有审核通过，负责人无法操作
        //不设置total,审核不通过，不用计算total
        if(expenseAccount.getStatus()==2)
        {
            expenseAccount.setStatus(4);
            return saveOrUpdate(expenseAccount) && reviewerExpenseAccountRelationMapper.add(reviewerExpenseAccountRelation);
        }
        return false;
    }

    @Override
    public boolean revokeExpenseAccountById(Long expenseAccountId, String masterUsername) {
        ExpenseAccount expenseAccount = getById(expenseAccountId);
        ReviewerExpenseAccountRelation reviewerExpenseAccountRelation=reviewerExpenseAccountRelationMapper.selectByExpenseAccountId(expenseAccountId);
        if(expenseAccount.getStatus()==3 && reviewerExpenseAccountRelation.getStatus()==1)
        {
            expenseAccount.setStatus(6);
            return saveOrUpdate(expenseAccount) && reviewerExpenseAccountRelationMapper.update(masterUsername,new Date(),expenseAccountId);
        }
        return false;
    }

    @Override
    public Account2Print getAccount2PrintByExpenseAccountId(Long expenseAccountId) {
        Account2Print account2Print=account2PrintMapper.getAccount2PrintByExpenseAccountId(expenseAccountId);
        account2Print.setRate(account2Print.getTotal()/(account2Print.getInvoiceFee()+account2Print.getRegistFee()));
        return account2Print;
    }

}
