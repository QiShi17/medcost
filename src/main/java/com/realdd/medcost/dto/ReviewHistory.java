package com.realdd.medcost.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by duanduan on 2020/11/16 16:08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ReviewHistory对象",description = "审批记录")
public class ReviewHistory implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty(value = "审核人学工号")
    private String reviewerIdNum;


    @ApiModelProperty(value = "审核人姓名")
    private String reviewerName;

    private Long expenseAccountId;

    @ApiModelProperty(value = "审核负责人学工号")
    private String reviewerMasterIdNum;

    @ApiModelProperty(value = "审核负责人姓名")
    private String reviewerMasterName;

    @ApiModelProperty(value = "修改意见")
    private String comment;

    @ApiModelProperty(value = "状态")
    private Integer status;

    private Integer isCancel;

    @ApiModelProperty(value = "报销总金额")
    private Double total;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;


}
