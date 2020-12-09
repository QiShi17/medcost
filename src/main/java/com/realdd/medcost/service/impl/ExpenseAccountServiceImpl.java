package com.realdd.medcost.service.impl;

import com.realdd.medcost.common.utils.SerialNumberUtil;
import com.realdd.medcost.dto.AccountDetail;
import com.realdd.medcost.entity.ExpenseAccount;
import com.realdd.medcost.entity.ReviewerExpenseAccountRelation;
import com.realdd.medcost.entity.Role;
import com.realdd.medcost.mapper.AccountDetailMapper;
import com.realdd.medcost.mapper.ExpenseAccountMapper;
import com.realdd.medcost.mapper.ReviewerExpenseAccountRelationMapper;
import com.realdd.medcost.mapper.RoleMapper;
import com.realdd.medcost.service.ExpenseAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
    public boolean agreeExpenseAccountById(Long expenseAccountId,String reviewerUsername) {
        System.out.println("名字是"+reviewerUsername);
        ExpenseAccount expenseAccount = getById(expenseAccountId);
        ReviewerExpenseAccountRelation reviewerExpenseAccountRelation=new ReviewerExpenseAccountRelation();
        reviewerExpenseAccountRelation.setExpenseAccountId(expenseAccountId);
        reviewerExpenseAccountRelation.setCreateTime(new Date());
        reviewerExpenseAccountRelation.setUpdateTime(reviewerExpenseAccountRelation.getCreateTime());
        reviewerExpenseAccountRelation.setReviewerIdNum(reviewerUsername);
        reviewerExpenseAccountRelation.setTotal(expenseAccount.getInvoiceFee()+expenseAccount.getRegistFee());
        reviewerExpenseAccountRelation.setStatus(1);        //审核通过
        reviewerExpenseAccountRelation.setIsCancel(0);      //审核负责人没有取消申请通过的状态
        //不设置comment,因为直接审核通过，没有修改意见
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
}
