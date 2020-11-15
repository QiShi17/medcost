package com.realdd.medcost.dto;


import com.realdd.medcost.entity.ReviewerExpenseAccountRelation;
import io.swagger.annotations.ApiModelProperty;


import java.util.Date;
import java.util.List;

/**
 *详情页Dto
 * @Author Weiser
 * @since 2020/11/12
 *
 */


public class AccountDetail {

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "流水号（时间戳17+随机码3）")
    private String serialNum;

    @ApiModelProperty(value = "报销类型")
    private Integer expenseTypeId;

    @ApiModelProperty(value = "用户姓名")
    private String realname;

    @ApiModelProperty(value = "学工号")
    private String username;

    @ApiModelProperty(value = "性别")
    private Integer gender;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "用户类型（员工/学生）")
    private Integer type;

    @ApiModelProperty(value = "部门")
    private String department;

    @ApiModelProperty(value = "专业")
    private String major;

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

    @ApiModelProperty(value = "挂号时间")
    private Date registTime;

    @ApiModelProperty(value = "挂号费用")
    private Double registFee;

    @ApiModelProperty(value = "挂号单照片")
    private String registImg;

    private String disease;

    private String prescriptionImg;

    @ApiModelProperty(value = "发票时间")
    private Date invoiceTime;

    @ApiModelProperty(value = "发票费用")
    private Double invoiceFee;

    private List<ReviewerExpenseAccountRelation> reviewerExpenseAccountRelationList;

}
