package com.hugh.user.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("用户显示信息")
@Data
public class UserVO {
    @ApiModelProperty(required = true, value = "源ID")
    private long id;
    @ApiModelProperty(required = true, value = "用户名称")
    private String name;
    @ApiModelProperty(required = true, value = "年龄")
    private int age;
    @ApiModelProperty(required = true, value = "余额")
    private double money;
}
