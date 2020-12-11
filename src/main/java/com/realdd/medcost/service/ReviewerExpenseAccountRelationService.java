package com.realdd.medcost.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.realdd.medcost.dto.ReviewStatistic;
import com.realdd.medcost.entity.ReviewerExpenseAccountRelation;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 审核人报销单关系表 服务类
 * </p>
 *
 * @author cute_duanduan
 * @since 2020-11-12
 */
public interface ReviewerExpenseAccountRelationService extends IService<ReviewerExpenseAccountRelation> {

    Page<ReviewStatistic> getReviewStatistic(Integer pageSize, Integer pageNum);

}
