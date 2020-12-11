package com.realdd.medcost.mapper;

import com.realdd.medcost.entity.ExpenseAccount;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.realdd.medcost.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 报销单 Mapper 接口
 * </p>
 *
 * @author cute_duanduan
 * @since 2020-11-08
 */
public interface ExpenseAccountMapper extends BaseMapper<ExpenseAccount> {

    //通过报销单id找到审核人报销单关系表中的报销金额
    Double selectExpenseAccountTotal(@Param("expenseAccountId")Long expenseAccountId);

    //通过报销单id找到user中的年度报销金额
    Double selectAnnualExpense(@Param("expenseAccountId")Long expenseAccountId);

    //通过报销单id找到user
    User selectUser(@Param("expenseAccountId")Long expenseAccountId);

}
