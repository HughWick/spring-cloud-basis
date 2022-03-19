package com.hugh.integration.control;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@Slf4j
@RestController
@RequestMapping("test")
public class TestControl {

    @GetMapping("/hello")
    public void test01(){
        System.out.println("===");
        log.info("测试提交git后，自动化部署！~！");
    }

}
