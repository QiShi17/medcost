package com.realdd.medcost.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.realdd.medcost.dto.ReviewStatistic;
import com.realdd.medcost.service.impl.ReviewerExpenseAccountRelationServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReviewerExpenseAccountRelationServiceTest {

    @Autowired
    ReviewerExpenseAccountRelationService relationService;

    @Test
    void getReviewStatistic(){
        Page<ReviewStatistic> reviewStatisticPage= relationService.getReviewStatistic(4,1);
        if(reviewStatisticPage!=null){
            System.out.println(reviewStatisticPage.getPages());
            System.out.println(reviewStatisticPage.getTotal());
            System.out.println(reviewStatisticPage.getSize());
            System.out.println(reviewStatisticPage.getCurrent());
            System.out.println(reviewStatisticPage.getRecords());
        }
    }

}
