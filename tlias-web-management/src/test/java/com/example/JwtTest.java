package com.example;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {

    @Test
    public void testGenerateJwt(){
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("id",1);
        dataMap.put("username","admin");

        String  jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,"amlld3U=")  //指定签名算法和密钥
                .addClaims(dataMap) //添加数据
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 1)) ///设置过期时间
                .compact(); //生成token 令牌
        System.out.println(jwt);
    }

    /**
     * 解析jwt令牌
     */
    @Test
    public void testParseJwt(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJhZG1pbiIsImV4cCI6MTc0OTYxODA5OH0.hJKMcYJPZkBlGPxR1-TXHVV40QGveX_Q4T01MNkDyVI";
        Claims claims = Jwts.parser()
                .setSigningKey("amlld3U=") //指定密钥
                .parseClaimsJws(token) //指定要解析的token令牌
                .getBody(); //获取数据
        System.out.println(claims);
    }
}
