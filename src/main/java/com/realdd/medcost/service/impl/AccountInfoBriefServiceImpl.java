package com.realdd.medcost.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.realdd.medcost.dto.AccountInfoBrief;
import com.realdd.medcost.mapper.AccountInfoBriefMapper;
import com.realdd.medcost.mapper.ReviewHistoryMapper;
import com.realdd.medcost.service.AccountInfoBriefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by duanduan on 2020/11/16 15:39
 */

@Service("AccountInfoBriefService")
public class AccountInfoBriefServiceImpl implements AccountInfoBriefService {

    @Autowired
    AccountInfoBriefMapper accountInfoBriefMapper;

    @Autowired
    ReviewHistoryMapper reviewHistoryMapper;

    @Override
    public Page<AccountInfoBrief> getAccountInfoBriefPage(Integer pageSize,Integer pageNum) {
        Page<AccountInfoBrief> accountInfoBriefPage=accountInfoBriefMapper.userAndAccountInfoPage(new Page<>(pageNum,pageSize));
        List<AccountInfoBrief> accountInfoBriefList=accountInfoBriefPage.getRecords();
        for (AccountInfoBrief accountInfoBrief:accountInfoBriefList) {
            accountInfoBrief.setReviewHistoryList(reviewHistoryMapper.listReviewHistoryByAccountId(accountInfoBrief.getId()));
        }
        return accountInfoBriefPage;
    }

    @Override
    public Page<AccountInfoBrief> getAccountInfoBriefPageReview(Integer pageSize, Integer pageNum,String userIdNum,Integer status,String excludeUsername) {
        List<Long> accountList=null;
        Page<AccountInfoBrief> accountInfoBriefPage=null;
        if(userIdNum!=null){
            accountList= reviewHistoryMapper.listReviewHistoryByReviewerId(userIdNum);
            if(accountList.size()==0) {
                accountInfoBriefPage=new Page<AccountInfoBrief>();
                accountInfoBriefPage.setSize(pageSize);
                accountInfoBriefPage.setCurrent(1);
                return accountInfoBriefPage;
            }
        }
        accountInfoBriefPage=accountInfoBriefMapper.userAndAccountInfoPageReview(new Page<>(pageNum,pageSize),accountList,status,excludeUsername);
        return accountInfoBriefPage;
    }

    @Override
    public Page<AccountInfoBrief> getAccountInfoBriefPageSubmit(Integer pageSize, Integer pageNum, String username, Integer status) {
        Page<AccountInfoBrief> accountInfoBriefPage=accountInfoBriefMapper.userAndAccountInfoPageSubmit(new Page<>(pageNum,pageSize),username,status);
        return accountInfoBriefPage;
    }
}
