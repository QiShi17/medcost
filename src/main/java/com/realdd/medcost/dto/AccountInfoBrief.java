package com.realdd.medcost.dto;

import com.realdd.medcost.entity.ReviewerExpenseAccountRelation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by duanduan on 2020/11/16 13:47
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain=true)
@ApiModel(value="AccountInfoBrief对象",description = "报销单简略信息")
public class AccountInfoBrief implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty(value = "流水号（时间戳17+随机码3）")
    private String serialNum;

    @ApiModelProperty(value = "报销类型")
    private Integer expenseTypeId;

    @ApiModelProperty(value = "报销类型名")
    private String name;

    @ApiModelProperty(value = "关联学工号")
    private String username;

    @ApiModelProperty(value = "报销人姓名（用户表）")
    private String realname;

    @ApiModelProperty(value = "病症")
    private String disease;

    @ApiModelProperty(value = "挂号费用")
    private Double registFee;

    @ApiModelProperty(value = "发票费用")
    private Double invoiceFee;

    @ApiModelProperty(value = "审核历史")
    private List<ReviewHistory> reviewHistoryList;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

}
