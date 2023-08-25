package com.github.hugh.auth.control;

import cn.dev33.satoken.stp.StpUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author AS
 * @date 2023/8/21 14:30
 */
@Api(tags = "登录")
@RestController
//@RequestMapping("/user/")
public class LoginControl {

    // 测试登录，浏览器访问： http://localhost:8081/user/doLogin?username=zhang&password=123456
    @GetMapping("doLogin")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "账号", required = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataTypeClass = Integer.class)
    })
    public String doLogin(String username, String password) {
        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
        if("zhang".equals(username) && "123456".equals(password)) {
            StpUtil.login(10001);
            StpUtil.getTokenInfo();
            return "登录成功";
        }
        return "登录失败";
    }

    // 查询登录状态，浏览器访问： http://localhost:8081/user/isLogin
    @GetMapping("isLogin")
    public String isLogin() {
        return "当前会话是否登录：" + StpUtil.isLogin();
    }
}
