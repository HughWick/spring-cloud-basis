//package com.github.hugh.quartz.contorl;
//
//import com.alibaba.csp.sentinel.annotation.SentinelResource;
//import com.github.hugh.quartz.service.UserTestService;
//import io.seata.spring.annotation.GlobalTransactional;
//import io.swagger.annotations.Api;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.Resource;
//
///**
// * @author AS
// * @date 2023/8/22 15:44
// */
//@Slf4j
//@Api(tags = "登录")
//@RestController
//@RequestMapping("user")
//public class TestControl {
//
//    @Resource
//    private UserTestService userTestService;
//    @GlobalTransactional
//    @GetMapping("get")
//    @SentinelResource(value = "get",blockHandler = "handleException")
//    public String get(String username) {
//        userTestService.approve(username);
//        return "success";
//    }
//
//}
