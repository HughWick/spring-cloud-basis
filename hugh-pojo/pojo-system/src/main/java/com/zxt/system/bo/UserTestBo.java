package com.zxt.system.bo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;


/**
 * @author AS
 * @date 2023/8/23 14:39
 */
@Data
@TableName("user_test")
public class UserTestBo {

    private String name;
    private int age;
    private int status;
    private int id;
    private Date created;
}
