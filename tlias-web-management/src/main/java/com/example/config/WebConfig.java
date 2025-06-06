package com.example.config;

import com.example.interceptor.DemoInterceptor;
import com.example.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Queue;
/*

@Configuration
public class WebConfig implements WebMvcConfigurer {

//    @Autowired
//    private DemoInterceptor demoInterceptor;
    @Autowired
    private TokenInterceptor tokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(demoInterceptor)
//                .addPathPatterns("/**"); //   /**拦截所有请求   /*只匹配一级路径
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**") //   /**拦截所有请求   /*只匹配一级路径
                .excludePathPatterns("/login"); // 放行登录接口
    }
}
*/
