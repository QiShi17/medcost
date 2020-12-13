package com.realdd.medcost.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 审核统计
 *
 * @Author huangxiaohou
 * @since 2020/12/9
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "AccountDetail对象", description = "报销单详情信息")
public class ReviewStatistic implements Serializable {

    private static final long serialVersionUID = 1L;

//    @TableId(value = "id",type = IdType.AUTO)
//    private Long id;

    @ApiModelProperty(value = "审核人学工号")
    private String reviewerIdNum;

    @ApiModelProperty(value = "审核人用户名")
    private String reviewerRealName;

    @ApiModelProperty(value = "审核人审核数")
    private Integer tcount;

    @ApiModelProperty(value = "审核人审核错误数")
    private Integer mcount;

    @ApiModelProperty(value = "审核人错误率")
    private Float percent;

}