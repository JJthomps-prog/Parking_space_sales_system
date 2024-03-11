package com.ts.parking.config;

import com.ts.parking.interceptor.RedisUrlCountInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AdminInterceptor implements WebMvcConfigurer {
    @Autowired
    RedisUrlCountInterceptor redisUrlCountInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
/*        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**")//所以请求会被拦截包括静态资源
                .excludePathPatterns("/","/login","/css/**","/fonts/**","/images/**","/js/**","/lib/**","/webfonts/**");//不拦截的资源

        registry.addInterceptor(redisUrlCountInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/","/login","/css/**","/fonts/**","/images/**","/js/**","/lib/**","/webfonts/**");*/
    }
}