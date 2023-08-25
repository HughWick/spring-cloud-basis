package com.github.hugh.quartz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


/**
 * @Date 2023/08/25
 * @note: 系统服务
 */
//Nacos服务注册
@EnableDiscoveryClient
//开启Feign调用
@EnableFeignClients(basePackages = {"com.github.hugh"})
//开启mapper扫描
@MapperScan("com.github.hugh.quartz.mapper")
//开启异步调用
//@EnableAsync
@SpringBootApplication(scanBasePackages = "com.github.hugh")
public class HughSystemQuartzApplication {

    public static void main(String[] args) {
        SpringApplication.run(HughSystemQuartzApplication.class, args);
    }
}
