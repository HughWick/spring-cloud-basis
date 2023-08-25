# Spring cloud 微服务基础框架

## 1.架构简介
* 基于SpringBoot、SpringCloud、SpringCloud Alibaba、Mybatis-plus构建核心架构
* 采用sa-token国内开源权限框架进行统一的Token下发与鉴权，保证系统安全性
* 微服务统一注册至Nacos，Nacos担任注册中心与配置中心的角色
* 采用Feign进行远程调用，Ribbon进行负载，Hystrix进行熔断
* 采用Sentinel进行限流，保障系统整体的性能
* 集成Seata，为分布式事务保驾护航
* 具有日志收集与监控服务为一体的能力
* 支持Docker、K8s、阿里云等多种部署方式

## 2、组件版本
| 中间件 | 版本        |
|-----|-----------|
| JDK | 11        |
| SpringBoot | 2.6.3     |
| Spring Cloud | 2021.0.5  |
| Spring Cloud Alibaba | 2021.0.5.0 |
| Seata | 1.6.1     |
| sa-token | 1.34.0    |