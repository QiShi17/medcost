package com.realdd.medcost.mapper;

import com.realdd.medcost.entity.ReviewerExpenseAccountRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * <p>
 * 审核人报销单关系表 Mapper 接口
 * </p>
 *
 * @author cute_duanduan+
 *
 * @since 2020-11-12
 */
public interface ReviewerExpenseAccountRelationMapper extends BaseMapper<ReviewerExpenseAccountRelation> {

    boolean add(ReviewerExpenseAccountRelation reviewerExpenseAccountRelation);

    ReviewerExpenseAccountRelation selectByExpenseAccountId(@Param("expenseAccountId")Long userId);

    boolean update(@Param("masterUsername")String masterUsername,
                   @Param("updateTime")Date updateTime,
                   @Param("expenseAccountId")Long expenseAccountId);
}