package com.hugh.user.control;

import com.github.hugh.util.EntityUtils;
import com.hugh.user.model.User;
import com.hugh.user.model.vo.UserVO;
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
    @ApiOperation(value = "查询用户信息")
    @GetMapping("/simple/{id}")
    public UserVO find(@PathVariable long id) {
        User user = userService.find(id);
        return EntityUtils.copy(user , UserVO::new);
    }

}
