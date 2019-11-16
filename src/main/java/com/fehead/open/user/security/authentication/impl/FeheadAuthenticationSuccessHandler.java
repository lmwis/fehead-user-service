package com.fehead.open.user.security.authentication.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fehead.open.user.security.authentication.Authentication;
import com.fehead.open.user.security.authentication.AuthenticationSuccessHandler;
import com.fehead.open.user.security.authentication.JWTAuthenticationFilter;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @Description: 认证成功处理器实现
 * @Author lmwis
 * @Date 2019-11-15 21:19
 * @Version 1.0
 */
@Component
@RequiredArgsConstructor
public class FeheadAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private static final Logger logger = LoggerFactory.getLogger(FeheadAuthenticationSuccessHandler.class);

    final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.info("登陆成功:" + authentication.getName());

        String token = Jwts.builder()
                .setSubject(authentication.getName())
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000))
                .signWith(SignatureAlgorithm.HS512, JWTAuthenticationFilter.SINGE_KEY)
                .compact();

        // 设置请求头并返回
        response.setContentType("application/json;charset=UTF-8");
        response.addHeader(JWTAuthenticationFilter.authorizationHeader, "Bearer " + token);
        response.setHeader("Access-Control-Expose-Headers", JWTAuthenticationFilter.authorizationHeader);
        response.getWriter().write(objectMapper.writeValueAsString(authentication));

    }
}
