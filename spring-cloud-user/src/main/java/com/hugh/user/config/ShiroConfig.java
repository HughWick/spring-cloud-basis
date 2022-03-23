package com.hugh.user.config;

import com.hugh.user.shiro.UserRealm;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * shiro配置信息
 *
 * @author AS
 * @date 2020/7/10 14:05
 */
@Slf4j
@Configuration
public class ShiroConfig {
    /**
     * session超时时间
     * <p>单位毫秒</p>
     */
    private static final long SESSION_TIME_OUT = 1800000L;
    //    @Resource
//    private SpringConfig springConfig;
    @Resource
    private JedisPool jedisPool;

    UserRealm userRealm() {
        UserRealm realm = new UserRealm();
//        realm.setAuthorizationCacheName("authorization");
        return realm;
    }


    /**
     * 创建安全管理器对象,关联自定义Realm
     */
    @Bean
    public DefaultWebSecurityManager securityManager(DefaultWebSessionManager redisSessionManager, RedisCacheManager redisCacheManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        List<Realm> realms = new ArrayList<>();
        //多个realm
        realms.add(userRealm());
        securityManager.setSessionManager(redisSessionManager);
        securityManager.setCacheManager(redisCacheManager);
        securityManager.setRealms(realms);
        return securityManager;
    }

    /**
     * 配置shiro redisManager
     * 使用的是shiro-redis开源插件
     *
     * @return RedisManager
     */
    @Bean
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setJedisPool(jedisPool);
        return redisManager;
    }

    @Bean
    public RedisSessionDAO redisSessionDAO(RedisManager redisManager) {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager);
        return redisSessionDAO;
    }

    /**
     * shiro session的管理
     * shiro自动存储cookie
     */
    @Bean
    public DefaultWebSessionManager redisSessionManager(RedisSessionDAO redisSessionDAO) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO);
//        sessionManager.setSessionIdCookie(sessionIdCookie());
        sessionManager.setGlobalSessionTimeout(SESSION_TIME_OUT);//设置session超时时间
        return sessionManager;
    }

    /**
     * 配置保存sessionId的cookie
     * 注意：这里的cookie 不是上面的记住我 cookie 记住我需要一个cookie session管理 也需要自己的cookie
     *
     * @return SimpleCookie
     */
    public SimpleCookie sessionIdCookie() {
        SimpleCookie cookie = new SimpleCookie();
//        if (OsUtils.isWindows() || RegexUtils.isIp(springConfig.getShiroDomain())) {
////            cookie.setPath("/"); //设置cookie路径
//        } else {
//            log.info("shiro domain:{}", springConfig.getShiroDomain());
//            cookie.setDomain("localhost");//子域名
//        }
        cookie.setHttpOnly(false);
        cookie.setPath("/"); //设置cookie路径
        return cookie;
    }

    @Bean
    public RedisCacheManager redisCacheManager(RedisManager redisManager) {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager);
        return redisCacheManager;
    }

    /**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     *
     * @param securityManager
     * @return AuthorizationAttributeSourceAdvisor
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    //--------------------------上述方法都可以封装成统一引用类---------------

    @Bean
    protected ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // 注意过滤器配置顺序不能颠倒
        // 配置过滤:不会被拦截的链接
        filterChainDefinitionMap.put("/static/**", "anon");
        //--------------
        filterChainDefinitionMap.put("/test/**", "anon");
        filterChainDefinitionMap.put("/**/*", "anon");
//        filterChainDefinitionMap.put("/v2/**/**", "anon");
        //--------------
        filterChainDefinitionMap.put("/configuration/**", "anon");
        filterChainDefinitionMap.put("/swagger*", "anon");
        // 对所有用户认证
        filterChainDefinitionMap.put("/**", "authc");

        // 配置shiro默认登录界面地址，前后端分离中登录界面跳转应由前端路由控制，后台仅返回json数据
        shiroFilterFactoryBean.setLoginUrl("/unauthorized");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }
}
