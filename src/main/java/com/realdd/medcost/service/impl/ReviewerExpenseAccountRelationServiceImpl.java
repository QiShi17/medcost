package com.realdd.medcost.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.realdd.medcost.dto.AccountInfoBrief;
import com.realdd.medcost.dto.ReviewStatistic;
import com.realdd.medcost.entity.ExpenseAccount;
import com.realdd.medcost.entity.ReviewerExpenseAccountRelation;
import com.realdd.medcost.entity.User;
import com.realdd.medcost.mapper.ReviewHistoryMapper;
import com.realdd.medcost.mapper.ReviewerExpenseAccountRelationMapper;
import com.realdd.medcost.service.ReviewerExpenseAccountRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 审核人报销单关系表 服务实现类
 * </p>
 *
 * @author cute_duanduan
 * @since 2020-11-12
 */
@Service
public class ReviewerExpenseAccountRelationServiceImpl extends ServiceImpl<ReviewerExpenseAccountRelationMapper, ReviewerExpenseAccountRelation> implements ReviewerExpenseAccountRelationService {

    @Autowired
    ReviewerExpenseAccountRelationMapper relationMapper;

    @Override
    public Page<ReviewStatistic> getReviewStatistic(Integer pageSize, Integer pageNum) {
        Page<ReviewStatistic> reviewStatisticPage = relationMapper.selectReview(new Page<>(pageSize,pageNum));
        return reviewStatisticPage;
    }
}
