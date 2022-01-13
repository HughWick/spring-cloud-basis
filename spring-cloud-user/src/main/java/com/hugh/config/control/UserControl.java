package com.hugh.config.control;

import com.hugh.config.model.User;
import com.hugh.config.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author AS
 * @date 2020/9/7 14:50
 */
@RestController
@RequestMapping("/user")
public class UserControl {

    @Value("${server.port}")
    private int port;

    @Resource
    private UserService userService;

    @RequestMapping("/simple/{id}")
    public User find(@PathVariable long id) {
        System.out.println("==端口=>" + port + "--线程--" + Thread.currentThread().getName());
        return userService.find(id);
    }

}
