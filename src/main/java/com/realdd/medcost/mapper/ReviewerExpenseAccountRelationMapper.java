package com.realdd.medcost.mapper;

import com.realdd.medcost.dto.AccountInfoBrief;
import com.realdd.medcost.dto.ReviewHistory;
import com.realdd.medcost.dto.ReviewStatistic;
import com.realdd.medcost.entity.ReviewerExpenseAccountRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 * 审核人报销单关系表 Mapper 接口
 * </p>
 *
 * @author cute_duanduan
 *
 * @since 2020-11-12
 */
public interface ReviewerExpenseAccountRelationMapper extends BaseMapper<ReviewerExpenseAccountRelation> {

    //查看审核统计
    Page<ReviewStatistic> selectReview (Page<ReviewStatistic> page);

}