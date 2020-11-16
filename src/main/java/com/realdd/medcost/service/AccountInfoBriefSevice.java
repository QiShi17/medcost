package com.realdd.medcost.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.realdd.medcost.dto.AccountInfoBrief;
import org.springframework.stereotype.Service;

/**
 * Created by duanduan on 2020/11/16 13:58
 */

public interface AccountInfoBriefSevice {
    Page<AccountInfoBrief> getAccountInfoBriefPage(Integer pageSize,Integer pageNum);

}
