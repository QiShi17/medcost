package com.realdd.medcost.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.realdd.medcost.dto.AccountInfoBrief;
import com.realdd.medcost.mapper.AccountInfoBriefMapper;
import com.realdd.medcost.mapper.ReviewHistoryMapper;
import com.realdd.medcost.service.AccountInfoBriefSevice;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by duanduan on 2020/11/16 15:39
 */

@Service("AccountInfoBriefService")
public class AccountInfoBriefServiceImpl implements AccountInfoBriefSevice {

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
}
