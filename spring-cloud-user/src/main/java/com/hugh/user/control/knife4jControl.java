package com.hugh.user.control;

import com.hugh.user.model.User;
import com.hugh.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "测试文档")
@RestController
@RequestMapping("/knife4j")
public class knife4jControl {

    @Resource
    private UserService userService;

    @ApiImplicitParam(name = "id", value = "用户id", required = true)
    @ApiOperation(value = "查询信息")
    @GetMapping("/simple/{id}")
    public User find(@PathVariable long id) {
//        System.out.println("==端口=>" + port + "--线程--" + Thread.currentThread().getName());
        return userService.find(id);
    }



}
