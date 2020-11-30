package com.realdd.medcost.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.realdd.medcost.dto.AccountInfoBrief;
import org.springframework.stereotype.Service;

/**
 * Created by duanduan on 2020/11/16 13:58
 */

public interface AccountInfoBriefService {
    Page<AccountInfoBrief> getAccountInfoBriefPage(Integer pageSize,Integer pageNum);
    Page<AccountInfoBrief> getAccountInfoBriefPageReview(Integer pageSize,
                                                         Integer pageNum,
                                                         String userIdNum,
                                                         Integer status,
                                                         String excludeUsername);
    Page<AccountInfoBrief> getAccountInfoBriefPageSubmit(Integer pageSize,
                                                         Integer pageNum,
                                                         String username,
                                                         Integer status);
}
