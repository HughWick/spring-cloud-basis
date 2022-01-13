package com.hugh.config.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author AS
 * @date 2020/9/7 11:52
 */
@Data
public class User implements Serializable {

    private long id;
    private String name;
    private int age;
    private double money;
    
}
