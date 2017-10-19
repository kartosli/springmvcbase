package com.n5x.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 基础控制器
 */
public abstract class BaseController {

    @Autowired
    public HttpServletRequest request;

    @Autowired
    public HttpServletResponse response;

    @Autowired
    public HttpSession session;

    private final static String SUCCESS = "success";
    private final static String FAIL = "fail";

    /**
     * 设置cookie(有效期默认为一周)
     *
     * @param response
     * @param name
     * @param value
     */
    public void setCookie(HttpServletResponse response, String name, String value) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(60 * 60 * 24 * 7);
        response.addCookie(cookie);
    }

    /**
     * 设置cookie
     *
     * @param response
     * @param name
     * @param value
     * @param maxAge   有效期(秒为单位)
     */
    public void setCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    /**
     * 获取cookie值
     *
     * @param request
     * @param name
     * @return
     */
    public String getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equals(name)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }


    public String getSessionId() {
        HttpSession session = request.getSession(true);
        String sessionId = null;
        if (session != null) {
            sessionId = session.getId();
        }
        return sessionId;
    }

    public void setSession(String key, Object obj) {
        HttpSession session = request.getSession(true);
        if (session != null) {
            session.setAttribute(key,obj);
        }
    }
    public Object getSession(String key) {
        HttpSession session = request.getSession(true);
        if (session != null) {
            return session.getAttribute(key);
        }
        return null;
    }




    protected String getString(Object obj) {
        if (obj != null) {
            return obj.toString();
        } else {
            return "";
        }
    }


    //会生成小于100000的数
    public static String getRandomNumberPassword(){
        Random random = new Random();
        int x = random.nextInt(899999);
        x = x+100000;
        return String.valueOf(x);
    }


}
