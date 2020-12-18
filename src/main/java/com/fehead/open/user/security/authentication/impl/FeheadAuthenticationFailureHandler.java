package com.fehead.open.user.security.authentication.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fehead.lang.error.AuthenticationException;
import com.fehead.lang.response.AuthenticationReturnType;
import com.fehead.open.user.security.authentication.AuthenticationFailureHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description:
 * @Author lmwis
 * @Date 2019-11-15 21:20
 * @Version 1.0
 */
@Component
@RequiredArgsConstructor
public class FeheadAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private static final Logger logger = LoggerFactory.getLogger(FeheadAuthenticationFailureHandler.class);

    final ObjectMapper objectMapper ;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        logger.info("校验失败");

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper
                .writeValueAsString( AuthenticationReturnType
                        .create(exception.getErrorMsg()
                                ,HttpStatus.UNAUTHORIZED.value())));
    }
}
