package com.hugh.user.control;

import com.github.hugh.util.EntityUtils;
import com.github.hugh.util.ip.Ip2regionUtils;
import com.hugh.user.model.User;
import com.hugh.user.model.vo.UserVO;
import com.hugh.user.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/simple/{id}")
    public UserVO find(@PathVariable long id) {
        System.out.println("==端口=>" + port + "--线程--" + Thread.currentThread().getName());
        User user = userService.find(id);
        return EntityUtils.copy(user , UserVO::new);
    }

    public static void main(String[] args) {
        System.out.println(Ip2regionUtils.get("222.244.144.131"));
    }

}
