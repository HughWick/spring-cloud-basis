package com.github.hugh.auth;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;


/**
 * 权限认证服务
 *
 * @Date 2023/08/25
 */
@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        DruidDataSourceAutoConfigure.class,
        MongoAutoConfiguration.class
}, scanBasePackages = "com.github.hugh")
public class HughAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(HughAuthApplication.class, args);
    }
}
