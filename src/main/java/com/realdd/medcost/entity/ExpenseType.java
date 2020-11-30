package com.realdd.medcost.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 报销类型
 * </p>
 *
 * @author cute_duanduan
 * @since 2020-11-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ExpenseType对象", description="报销类型")
public class ExpenseType implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    @ApiModelProperty(value = "超额前报销比例")
    private Float fRatio;

    @ApiModelProperty(value = "超额后报销比例")
    private Float lRatio;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;


}
