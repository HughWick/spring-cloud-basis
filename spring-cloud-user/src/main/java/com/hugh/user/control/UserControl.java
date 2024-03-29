package com.hugh.user.control;

import com.alibaba.fastjson.JSON;
import com.github.hugh.bean.dto.Ip2regionDTO;
import com.github.hugh.bean.dto.ResultDTO;
import com.github.hugh.json.gson.JsonObjectUtils;
import com.github.hugh.json.gson.JsonObjects;
import com.github.hugh.util.EntityUtils;
import com.github.hugh.util.ServletUtils;
import com.github.hugh.util.ip.Ip2regionUtils;
import com.hugh.user.model.User;
import com.hugh.user.model.vo.Test2VO;
import com.hugh.user.model.vo.TestVO;
import com.hugh.user.model.vo.TestWomanVO;
import com.hugh.user.model.vo.UserVO;
import com.hugh.user.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
        return EntityUtils.copy(user, UserVO::new);
    }

    @PostMapping("/login")
    public ResultDTO find(String account, String password) {
        // 2.1 不存在则 使用账号密码登录
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken customizedToken = new UsernamePasswordToken(account, password);
        try {
            subject.login(customizedToken);
//            return new ResultDTO("0000", "登陆成功");
        } catch (IncorrectCredentialsException ice) {
//            return ResultUtils.error("用户不存在或者密码错误");
        } catch (LockedAccountException lae) {
//            return ResultUtils.error(ResultEnum.ACCOUNT_DISABLE.getMsg());
        } catch (AuthenticationException ae) {
//            return ResultUtils.error("登录失败，网络异常");
            ae.printStackTrace();
            return new ResultDTO("10000", "登录失败，网络异常");
        }
        return new ResultDTO("0000", "登陆成功");
    }

    @GetMapping("/parseIp/{ip}")
    public Ip2regionDTO find(@PathVariable String ip) {
        return Ip2regionUtils.parse(ip);
    }

    @GetMapping("/json")
    public String find(HttpServletRequest request) {
        System.out.println("--阿里json->>" + JSON.toJSONString(ServletUtils.getParams(request)));
        JsonObjects jsonObjects = new JsonObjects(ServletUtils.getParams(request).toString());
        System.out.println("gson--->" + jsonObjects);
        TestVO testVO = jsonObjects.formJson(TestVO.class);
        System.out.println("-json--->>" + JsonObjectUtils.toJson(testVO));
        return null;
    }

    // get获取对象示例demo， get无法直接解析对象中在包含对象的参数，使用第一个对象接收外层数据，在使用另外一个对象接收内部嵌套的对象进行解析
    @GetMapping("/object")
    public String object(Test2VO testVO12, @ModelAttribute("woman") TestWomanVO testWomanVO) {
        System.out.println("--object-->>" + JsonObjectUtils.toJson(testWomanVO));
        System.out.println("--testVO12-->>" + JsonObjectUtils.toJson(testVO12));
        return null;
    }
}
