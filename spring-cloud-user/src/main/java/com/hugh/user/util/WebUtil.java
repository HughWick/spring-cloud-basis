package com.hugh.user.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ResourceBundle;

/* Web层辅助类
 *
 * @author Alvis
 * @version 2016年4月2日 下午4:19:28
 */
public final class WebUtil {
    private WebUtil() {
    }

    /**
     * 当前用户
     */
    public static final String CURRENT_USER = "CURRENT_USER";

    /**
     * 获取指定Cookie的值
     *
     * @param cookieName   cookie名字
     * @param defaultValue 缺省值
     * @return
     */
    public static String getCookieValue(HttpServletRequest request, String cookieName, String defaultValue) {
        Cookie cookie = WebUtils.getCookie(request, cookieName);
        if (cookie == null) {
            return defaultValue;
        }
        return cookie.getValue();
    }


    /**
     * 保存当前用户
     */
    public static final void saveCurrentUser(Object user) {
        setSession(CURRENT_USER, user);
    }

    /**
     * 获取当前用户
     */
    public static final Object getCurrentUser() {
        Subject currentUser = SecurityUtils.getSubject();
        if (null != currentUser) {
            Session session = currentUser.getSession();
            if (null != session) {
                return session.getAttribute(CURRENT_USER);
            }
        }
        return null;
    }

    /**
     * 获取当前会话
     */
    public static Session getSession() {
        try {
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession(false);
            if (session == null) {
                session = subject.getSession();
            }
            if (session != null) {
                return session;
            }
        } catch (InvalidSessionException e) {

        }
        return null;
    }

    /**
     * 将一些数据放到ShiroSession中,以便于其它地方使用
     * 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到
     */
    public static final void setSession(Object key, Object value) {
        Subject currentUser = SecurityUtils.getSubject();
        if (null != currentUser) {
            Session session = currentUser.getSession();
            if (null != session) {
                session.setAttribute(key, value);
            }
        }
    }

    /**
     * 移除当前用户
     */
    public static final void removeCurrentUser(HttpServletRequest request) {
        request.getSession().removeAttribute(CURRENT_USER);
    }

    /**
     * 获得国际化信息
     *
     * @param key     键
     * @param request
     * @return
     */
    public static final String getApplicationResource(String key, HttpServletRequest request) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("ApplicationResources", request.getLocale());
        return resourceBundle.getString(key);
    }
}
