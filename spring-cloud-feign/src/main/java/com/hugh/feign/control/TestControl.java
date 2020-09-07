package com.hugh.feign.control;

import com.hugh.feign.service.UserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * @author AS
 * @date 2020/9/4 15:02
 */
@RestController
@RequestMapping("/feign")
public class TestControl {

    @Resource
    private UserService client;

    @RequestMapping(value = "/getUser/{id}", produces = "application/json; charset=UTF-8")
    public String getUser(@PathVariable  long id) {
        return client.getAllStudent(id);
    }
}
