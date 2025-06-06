package com.example.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtils {

    // 密钥（与测试类中一致）
    private static final String SECRET_KEY = "amlld3U=";

    // 过期时间：7天（毫秒）
    private static final long EXPIRATION = 1000 * 60 * 60 * 24 * 7; // 7天

    /**
     * 生成 JWT 令牌
     *
     * @param claims 自定义负载数据
     * @return 返回生成的 token 字符串
     */
    public static String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .addClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    /**
     * 解析 JWT 令牌，获取 Claims 数据
     *
     * @param token 需要解析的 token 字符串
     * @return 返回解析后的 Claims 对象
     */
    public static Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}