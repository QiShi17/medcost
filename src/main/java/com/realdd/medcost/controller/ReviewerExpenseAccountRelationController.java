package com.realdd.medcost.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.realdd.medcost.common.api.CommonPage;
import com.realdd.medcost.common.api.CommonResult;
import com.realdd.medcost.dto.AccountInfoBrief;
import com.realdd.medcost.dto.ReviewStatistic;
import com.realdd.medcost.entity.Department;
import com.realdd.medcost.entity.ReviewerExpenseAccountRelation;
import com.realdd.medcost.service.DepartmentService;
import com.realdd.medcost.service.ReviewerExpenseAccountRelationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 审核人报销单关系表 前端控制器
 * </p>
 *
 * @author cute_duanduan
 * @since 2020-11-12
 */
@RestController
@RequestMapping("/reviewer_expense_account_relation")
public class ReviewerExpenseAccountRelationController {

    @Autowired
    private ReviewerExpenseAccountRelationService relationService;

    @ApiOperation(value = "审核结果统计")
    @GetMapping("/ReviewResultStatistic")
    public CommonResult ReviewResultStatistic(@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                             @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page<ReviewStatistic> reviewStatisticPage = relationService.getReviewStatistic(pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(reviewStatisticPage));
    }

}
