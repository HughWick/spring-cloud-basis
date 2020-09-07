package com.hugh.feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author AS
 * @date 2020/9/4 15:27
 */
@FeignClient(name = "SPRING-CLOUD-USER")
public interface UserService {

    @RequestMapping(value = "/user/simple/{id}")
    String getAllStudent(@PathVariable("id") long id);
}
