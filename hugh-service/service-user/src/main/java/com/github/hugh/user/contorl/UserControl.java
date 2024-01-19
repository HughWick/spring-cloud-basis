package com.github.hugh.user.contorl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.github.hugh.user.service.UserTestService;
import io.seata.spring.annotation.GlobalTransactional;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author AS
 * @date 2023/8/22 15:44
 */
@Slf4j
@Api(tags = "用户信息")
@RestController
@RequestMapping("user")
public class UserControl {

    @Resource
    private UserTestService userTestService;

    /**
     *
     * @param username
     * @param flag 异常标识：0-异常
     * @return
     */
    @GlobalTransactional
    @GetMapping("insert")
    @SentinelResource(value = "insert", blockHandler = "handleException")
    public String insert(@RequestParam("username") String username ,@RequestParam(value = "flag" , defaultValue = "0") int flag ) {
        userTestService.approve(username ,flag);
        return "success";
    }



//    @GlobalTransactional
//    @GetMapping("get")
//    @SentinelResource(value = "get", blockHandler = "handleException")
//    public String get(@RequestParam("username") String username) {
//        userTestService.insert(username);
//        return "success";
//    }
}
