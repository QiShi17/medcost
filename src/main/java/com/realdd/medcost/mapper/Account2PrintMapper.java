package com.realdd.medcost.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.realdd.medcost.dto.Account2Print;
import com.realdd.medcost.dto.AccountDetail;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: Weiser
 * Date: 2020/12/13 10:35
 **/
public interface Account2PrintMapper extends BaseMapper<Account2Print> {

    Account2Print getAccount2PrintByExpenseAccountId(@Param("expenseAccountId")Long expenseAccountId);
}
