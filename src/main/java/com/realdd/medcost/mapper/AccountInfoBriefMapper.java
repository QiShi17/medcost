package com.realdd.medcost.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.realdd.medcost.dto.AccountInfoBrief;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by duanduan on 2020/11/16 13:59
 */
@Mapper
public interface AccountInfoBriefMapper {
    /**
     * 分页查询（报销单列表信息）
     */
    Page<AccountInfoBrief> userAndAccountInfoPage(Page<AccountInfoBrief> page);
}
