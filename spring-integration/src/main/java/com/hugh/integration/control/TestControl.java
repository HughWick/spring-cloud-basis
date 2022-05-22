package com.hugh.integration.control;

import cn.hutool.db.nosql.redis.RedisDS;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

@Validated
@Slf4j
@RestController
@RequestMapping("test")
public class TestControl {

    @GetMapping("/hello")
    public void test01(){
        System.out.println("==123是分公司的=");
        log.info("测试提交git后，自动化部署！~！");
        Jedis jedis = RedisDS.create().getJedis();
    }

}
