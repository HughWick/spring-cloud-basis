package com.github.hugh.system.dao;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author AS
 * @date 2024/1/18 14:41
 */
@Data
@TableName("user_test")
public class UserTestDo {
    private Integer id;
//    @TableField("name")
    private String name;
//    @TableField("age")
    private int age;
//    @TableField("status")
    private int status;
//    @TableField("created")
    private Date created;
}
