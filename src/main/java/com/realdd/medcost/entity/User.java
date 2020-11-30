package com.realdd.medcost.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author cute_duanduan
 * @since 2020-10-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="User对象", description="用户表")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Excel(name = "学工号")
    @ApiModelProperty(value = "学工号")
    private String username;

    @Excel(name = "姓名")
    @ApiModelProperty(value = "用户姓名")
    private String realname;

    @Excel(name = "密码")
    @ApiModelProperty(value = "密码")
    private String password;

    @Excel(name = "性别（男/女）", replace = {"男_0", "女_1"})
    @ApiModelProperty(value = "性别")
    private Integer gender;

    @Excel(name = "年龄")
    @ApiModelProperty(value = "年龄")
    private Integer age;

    @Excel(name = "用户类型（员工/学生）", replace = {"员工_0", "学生_1"})
    @ApiModelProperty(value = "用户类型（员工/学生）")
    private Integer type;

    @Excel(name = "最后登录时间", exportFormat = "yyyy-MM-dd hh:mm:ss",importFormat = "yyyy-MM-dd hh:mm:ss")
    @ApiModelProperty(value = "最后登录时间")
    private Date loginTime;

    @Excel(name = "创建时间", exportFormat = "yyyy-MM-dd hh:mm:ss",importFormat = "yyyy-MM-dd hh:mm:ss",databaseFormat = "yyyy-MM-dd hh:mm:ss")
    @ApiModelProperty(value = "创建时间")
    @TableField(fill= FieldFill.INSERT)
    private Date createTime;

    @Excel(name = "学院")
    @ApiModelProperty(value = "学院")
    private String school;

    @Excel(name = "专业")
    @ApiModelProperty(value = "专业")
    private String major;

    @Excel(name = "年级")
    @ApiModelProperty(value = "年级")
    private Integer grade;

    @Excel(name = "部门")
    @ApiModelProperty(value = "部门")
    private String department;

    @Excel(name = "年度报销金额")
    @ApiModelProperty(value = "年度报销金额")
    private Double annualExpense;

    @Excel(name = "修改时间",exportFormat = "yyyy-MM-dd hh:mm:ss",importFormat = "yyyy-MM-dd hh:mm:ss")
    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;


}
