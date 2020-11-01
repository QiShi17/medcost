package com.realdd.medcost.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;

/**
 * 用户登录参数
 * Created by duanduan on 2020/10/24 19:15
 */
public class UserLoginParam {
    @ApiModelProperty(value = "学工号", required = true)
    @NotEmpty(message = "学工号不能为空")
    private String username;
    @ApiModelProperty(value = "密码", required = true)
    @NotEmpty(message = "密码不能为空")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
