package com.ykw.parking.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: tang
 * @Date: 2021/08/30/17:00
 * @Description: 记录访问网站次数
 */
@Component
public class RedisUrlCountInterceptor implements HandlerInterceptor {
    @Autowired
    RedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        if(url.equals("/index.html")) {
            stringRedisTemplate.opsForValue().increment(url);
        }
        return true;
    }
}
