package com.realdd.medcost.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: Weiser
 * Date: 2020/12/14 0:24
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain=true)
@ApiModel(value="AccountResultStatistic对象",description = "统计数据")
public class AccountResultStatistic implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;

    private List<String> departmentAndSchoolList;

    private List<Integer> departmentAndSchoolNumList;//各学院人数

    private List<Double> departmentAndSchoolTotalList;//各学院报销总额


}
