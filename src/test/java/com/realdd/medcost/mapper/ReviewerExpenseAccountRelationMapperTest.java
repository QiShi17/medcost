package com.realdd.medcost.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.realdd.medcost.dto.AccountInfoBrief;
import com.realdd.medcost.dto.ReviewHistory;
import com.realdd.medcost.dto.ReviewStatistic;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReviewerExpenseAccountRelationMapperTest {
    @Autowired
    ReviewerExpenseAccountRelationMapper reviewerExpenseAccountRelationMapper;

    @Test
    void list(){
        Page<ReviewStatistic> reviewStatisticPage = new Page<>(1,4);
        Page<ReviewStatistic> reviewStatistic=reviewerExpenseAccountRelationMapper.selectReview(reviewStatisticPage);
        if(reviewStatistic!=null){
            System.out.println(reviewStatistic.getPages());
            System.out.println(reviewStatistic.getTotal());
            System.out.println(reviewStatistic.getSize());
            System.out.println(reviewStatistic.getCurrent());
            System.out.println(reviewStatistic.getRecords());
        }
    }
}
