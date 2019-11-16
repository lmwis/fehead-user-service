package com.fehead.open.user.security.authentication;

import com.fehead.lang.error.AuthenticationException;
import com.fehead.lang.error.BusinessException;
import com.fehead.lang.error.EmBusinessError;
import com.fehead.open.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @Description: 只拦截 {/user/authentication}
 * @Author lmwis
 * @Date 2019-11-16 12:09
 * @Version 1.0
 */
@Component
public class UsernamePasswordAuthenticationFilter extends OncePerRequestFilter {

    /**
     * 认证失败处理器
     */
    private final AuthenticationFailureHandler authenticationFailureHandler;

    /**
     * 认证成功处理器
     */
    private final AuthenticationSuccessHandler authenticationSuccessHandler;

    /**
     * 密码匹配
     */
    private final PasswordEncoder passwordEncoder;

    private final String authenticationUri;

    private final UserService userService;

    public UsernamePasswordAuthenticationFilter(UserService userService
            , AuthenticationFailureHandler authenticationFailureHandler
            , AuthenticationSuccessHandler authenticationSuccessHandler
            , PasswordEncoder passwordEncoder) {
        this.authenticationUri = "/user/authentication";
        this.userService = userService;
        this.authenticationFailureHandler = authenticationFailureHandler;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String requestURI = request.getRequestURI();

        if (StringUtils.equals(authenticationUri, requestURI) && HttpMethod.POST==HttpMethod.resolve(request.getMethod())) { // 进行授权处理

            String username = request.getParameter("username");
            String principal = request.getParameter("principal");


            UserAccessAuthenticationToken requestUserAuthenticationToken;
            try {
                // 从数据库查询用户信息
                requestUserAuthenticationToken = userService.loadUser(username);
                // 密码校验
                if(passwordEncoder.matches(principal,requestUserAuthenticationToken.getCredentials())){ // 校验成功
                    // 构造成功token
                    UserAccessAuthenticationToken accessAuthenticationToken
                            = new UserAccessAuthenticationToken(
                                    new AuthenticationDetails(new Date().getTime(),request.getRemoteAddr())
                            ,true,new AuthenticationPrincipal(requestUserAuthenticationToken.getName(),"",true,true)
                            ,""
                            ,requestUserAuthenticationToken.getName());
                    // 校验成功处理器
                    authenticationSuccessHandler.onAuthenticationSuccess(request,response,accessAuthenticationToken);
                }else { // 密码错误
                    throw new AuthenticationException(EmBusinessError.USER_LOGIN_FAIL);
                }
            } catch (AuthenticationException e) {
                // 校验失败 用户不存在
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
            }
            // 请求返回
            return;
        }
        // 放行
        filterChain.doFilter(request,response);

    }
}
