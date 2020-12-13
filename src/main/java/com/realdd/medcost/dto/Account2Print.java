package com.realdd.medcost.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Weiser
 * 打印报销单
 * Date: 2020/12/12 17:23
 **/

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain=true)
@ApiModel(value="Account2Print对象",description = "打印报销单")
public class Account2Print implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty(value = "用户姓名")
    private String realname;

    @ApiModelProperty(value = "性别")
    private Integer gender;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "学工号")
    private String username;

    @ApiModelProperty(value = "用户类型（员工/学生）")
    private Integer type;

    @ApiModelProperty(value = "部门")
    private String department;

    @ApiModelProperty(value = "学院")
    private String school;

    @ApiModelProperty(value = "流水号（时间戳17+随机码3）")
    private String serialNum;

    @ApiModelProperty(value = "报销类型")
    private Integer expenseTypeId;

    @ApiModelProperty(value = "转诊前医院名")
    private String fHospitalName;

    @ApiModelProperty(value = "转诊后医院名")
    private String lHospitalName;

    @ApiModelProperty(value = "科室")
    private String room;

    @ApiModelProperty(value = "挂号时间")
    private Date registTime;

    @ApiModelProperty(value = "挂号费用")
    private Double registFee;

    @ApiModelProperty(value = "发票时间")
    private Date invoiceTime;

    @ApiModelProperty(value = "发票费用")
    private Double invoiceFee;

    private String disease;

    @ApiModelProperty(value = "报销总金额")
    private Double total;

    @ApiModelProperty(value = "报销比例")
    private Double rate;

    @ApiModelProperty(value = "审核人realname")
    private String reviewerRealName;

    @ApiModelProperty(value = "审核通过时间")
    private Date reviewerAgreeTime;//表单relation更新的时间
}
