package com.realdd.medcost.mapper;

import com.realdd.medcost.dto.ReviewHistory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by duanduan on 2020/11/16 16:20
 */
@Mapper
public interface ReviewHistoryMapper {
    /**
     * 根据报销单id查询审核历史
     */
    List<ReviewHistory> listReviewHistoryByAccountId(Long accountId);
}
