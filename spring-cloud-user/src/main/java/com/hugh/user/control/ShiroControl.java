package com.hugh.user.control;

import com.github.hugh.bean.dto.ResultDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "权限")
@RestController
@RequestMapping("/shiro")
public class ShiroControl {

    @ApiOperation(value = "登陆")
    @PostMapping("/login")
    public ResultDTO find(String account, String password) {
        // 2.1 不存在则 使用账号密码登录
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken customizedToken = new UsernamePasswordToken(account, password);
        try {
            subject.login(customizedToken);

            return new ResultDTO("0000", "登陆成功");
        } catch (IncorrectCredentialsException ice) {
//            ice.printStackTrace();
            return new ResultDTO("00001","用户不存在或者密码错误");
        } catch (LockedAccountException lae) {
            lae.printStackTrace();
//            return ResultUtils.error(ResultEnum.ACCOUNT_DISABLE.getMsg());
        } catch (AuthenticationException ae) {
//            return ResultUtils.error("登录失败，网络异常");
            ae.printStackTrace();
            return new ResultDTO("10000", "登录失败，网络异常");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultDTO("10000", "未知错误");
    }

    @RequiresRoles("admin")
    @GetMapping("/admin")
    public String admin() {
        return "admin roles success!";
    }

    @ApiOperation(value = "查询")
    @RequiresPermissions("query")
    @GetMapping("/query")
    public String query() {
        return "query success!";
    }

    @RequiresPermissions("add")
    @GetMapping("/add")
    public String add() {
        return "add success!";
    }
}
