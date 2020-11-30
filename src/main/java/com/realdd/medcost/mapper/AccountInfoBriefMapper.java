package com.realdd.medcost.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.realdd.medcost.dto.AccountInfoBrief;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by duanduan on 2020/11/16 13:59
 */
@Mapper
public interface AccountInfoBriefMapper {
    /**
     * 分页查询（报销单列表信息）
     */
    Page<AccountInfoBrief> userAndAccountInfoPage(Page<AccountInfoBrief> page);

    /**
     * 分页查询（审核报销单列表信息）
     */
    Page<AccountInfoBrief> userAndAccountInfoPageReview(@Param("page")Page<AccountInfoBrief> page , @Param("idList")List idList,@Param("status")Integer status,@Param("excludeUsername")String username);

    /**
     * 分页查询（提交报销单列表信息）
     */
    Page<AccountInfoBrief> userAndAccountInfoPageSubmit(@Param("page")Page<AccountInfoBrief> page , @Param("username")String username,@Param("status")Integer status);
}
