package com.realdd.medcost.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 报销单
 * </p>
 *
 * @author cute_duanduan
 * @since 2020-11-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ExpenseAccount对象", description="报销单")
public class ExpenseAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty(value = "流水号（时间戳17+随机码3）")
    private String serialNum;

    @ApiModelProperty(value = "转诊前医院")
    private Long fHospitalId;

    @ApiModelProperty(value = "转诊单照片")
    private String referralImg;

    @ApiModelProperty(value = "转诊后医院名")
    private String lHospitalName;

    @ApiModelProperty(value = "科室")
    private String room;

    @ApiModelProperty(value = "转诊单规定时间")
    private Date deadline;

    @ApiModelProperty(value = "报销类型")
    private Integer expenseTypeId;

    @ApiModelProperty(value = "关联学工号")
    private String username;

    @ApiModelProperty(value = "挂号单照片")
    private String registImg;

    @ApiModelProperty(value = "挂号时间")
    private Date registTime;

    @ApiModelProperty(value = "挂号费用")
    private Double registFee;

    @ApiModelProperty(value = "病症")
    private String disease;

    private String prescriptionImg;

    @ApiModelProperty(value = "发票照片")
    private String invoiceImg;

    @ApiModelProperty(value = "发票时间")
    private Date invoiceTime;

    @ApiModelProperty(value = "发票费用")
    private Double invoiceFee;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;


}
