package com.example.filter;

import com.example.utils.CurrentHolder;
import com.example.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebFilter(filterName = "tokenFilter",urlPatterns = "/*")
public class TokenFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //1.获取请求参数
        String requestURI  = request.getRequestURI();

        //2.判断参数是否合法,如果路径中包含/login,则放行
        if (requestURI.contains("/login")){
            log.info("登录请求,放行");
            filterChain.doFilter(request,response);
            return;
        }

        //3.获取请求头中的token令牌
        String token = request.getHeader("token");

        //4.判断token令牌是否为空,如果为空,说明没有登录,则返回错误信息
        if (token == null || token.isEmpty()){
            log.info("请求头token为空,返回错误信息");
            response.setStatus(401);
            return;
        }

        //5.解析token令牌,如果解析失败,则返回错误信息
        try {
            Claims claims = JwtUtils.parseToken(token);
            Integer id = Integer.valueOf(claims.get("id").toString());
            CurrentHolder.setCurrentId(id);
            log.info("当前登录员工ID: {},存入ThreadLocal", id);
        }catch (Exception e){
            log.info("解析token失败,返回错误信息");
            response.setStatus(401);
            return;
        }

        //6.解析成功,则放行
        log.info("请求通过,放行");
        filterChain.doFilter(request,response);

        //7.清空删除threadLocal中的数据
        CurrentHolder.remove();
    }
}
