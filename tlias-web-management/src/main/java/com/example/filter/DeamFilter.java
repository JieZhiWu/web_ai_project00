package com.example.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

//@WebFilter(urlPatterns = "/emps/*") //拦截所有请求
@Slf4j
public class DeamFilter implements Filter {
    //初始化方法,在服务器启动时执行一次
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("初始化方法");
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("拦截到了请求");
        //放行
        filterChain.doFilter(servletRequest,servletResponse);

    }

    //销毁方法,在服务器关闭时执行一次
    @Override
    public void destroy() {
        log.info("销毁方法");
        Filter.super.destroy();
    }
}
