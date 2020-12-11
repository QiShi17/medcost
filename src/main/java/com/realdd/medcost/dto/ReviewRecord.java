package com.realdd.medcost.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @Author: Weiser
 * Date: 2020/11/18 14:07
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ReviewRecord对象",description = "审批历史")
public class ReviewRecord {

    private Long id;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "审核人姓名")
    private String reviewerRealname;

    @ApiModelProperty(value = "审核人学工号")
    private String reviewerIdNum;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "修改意见")
    private String comment;                     //以上五条是详情页的历史审核记录需要的信息

}
