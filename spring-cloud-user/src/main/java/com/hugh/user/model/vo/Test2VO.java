package com.hugh.user.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("用户显示信息")
@Data
public class Test2VO {
    @ApiModelProperty(required = true, value = "用户名称")
    private String name;
    private String sex_in;

}
