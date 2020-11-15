package com.realdd.medcost.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 审核人报销单关系表
 * </p>
 *
 * @author cute_Weiser
 * @since 2020-11-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ReviewerExpenseAccountRelation对象", description="审核人报销单关系表")
public class ReviewerExpenseAccountRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty(value = "审核人学工号")
    private Long reviewerIdNum;

    private Long expenseAccountId;

    @ApiModelProperty(value = "审核负责人学工号")
    private Long reviewerMasterIdNum;

    @ApiModelProperty(value = "修改意见")
    private String comment;

    @ApiModelProperty(value = "状态")
    private Integer status;

    private Integer isCancel;

    @ApiModelProperty(value = "报销总金额")
    private Double total;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;


}
