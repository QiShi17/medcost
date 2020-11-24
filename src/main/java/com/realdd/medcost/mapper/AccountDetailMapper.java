package com.realdd.medcost.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.realdd.medcost.dto.AccountDetail;
import com.realdd.medcost.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: Weiser
 * Date: 2020/11/15 21:50
 **/



public interface AccountDetailMapper extends BaseMapper<AccountDetail> {

    List<AccountDetail> getAccountDetailByExpenseAccountId(@Param("expenseAccountId")Long expenseAccountId);
}
