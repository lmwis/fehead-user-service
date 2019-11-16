package com.fehead.open.user.security.authentication;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: 认证成功处理器接口
 * @Author lmwis
 * @Date 2019-11-15 21:21
 * @Version 1.0
 */
public interface AuthenticationSuccessHandler {

    void onAuthenticationSuccess(HttpServletRequest request,
                                 HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException;
}
