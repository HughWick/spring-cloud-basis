package com.github.hugh.service.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


/**
 * 系统微服务启动类
 *
 * @Date 2023/08/25
 */
//Nacos服务注册
@EnableDiscoveryClient
//开启Feign调用
@EnableFeignClients(basePackages = {"com.github"})
//开启mapper扫描
@MapperScan("com.github.hugh.service.system.mapper")
//开启异步调用
//@EnableAsync
@SpringBootApplication(scanBasePackages = "com.github")
public class HughSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(HughSystemApplication.class, args);
    }
}
