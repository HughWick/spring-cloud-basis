package com.hugh.user.model.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("用户显示信息")
@Data
public class TestWomanVO {
     private int age;
     private String name;
}
