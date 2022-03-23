package com.hugh.user.shiro;

import com.google.common.collect.Lists;
import com.hugh.user.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserRealm extends AuthorizingRealm {

    /**
     * 获取用户权限
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("-----验证权限---");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        List<String> item = Lists.newArrayList("find", "query");
//        UserBO userBO = (UserBO) principals.getPrimaryPrincipal();//获取实体
        info.addStringPermissions(item);
        return info;
    }

    /**
     * 获取用户校验登录是否正常存储Session
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken customizedToken = (UsernamePasswordToken) token;
        String userName = customizedToken.getUsername();
        Object credentials = customizedToken.getCredentials();
        // TODO 自行根据账号密码查询数据库，如果查询不到结果，则判断为null
        User user = null;
        if (userName.equals("123")) {
            user = new User();
        }
        if (user == null) {
            // 这里返回后会报出对应异常 IncorrectCredentialsException 不正确的凭证
            throw new IncorrectCredentialsException();
        } else {
            // TODO 查询到用户信息后的业务逻辑

            // 默认传递control 的账号密码即可
            return new SimpleAuthenticationInfo(userName, credentials, "user_info");
        }
    }
}
