package com.fehead.open.user.security.authentication;

import com.fehead.lang.error.BusinessException;
import com.fehead.lang.properties.FeheadProperties;
import com.fehead.lang.error.AuthenticationException;
import com.fehead.lang.error.EmBusinessError;
import com.fehead.open.user.properties.FeheadUserServiceProperties;
import com.fehead.open.user.service.UserService;
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
 * 认证拦截器
 * 参数：username,principal
 * done.
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

    private final String authenticateUri;

    private final UserService userService;

    private final FeheadUserServiceProperties feheadUserServiceProperties;

    public UsernamePasswordAuthenticationFilter(UserService userService
            , AuthenticationFailureHandler authenticationFailureHandler
            , AuthenticationSuccessHandler authenticationSuccessHandler
            , PasswordEncoder passwordEncoder
            , FeheadUserServiceProperties feheadUserServiceProperties) {
        this.authenticateUri = feheadUserServiceProperties.getAuthenticate().getAuthenticateUri();
        this.userService = userService;
        this.authenticationFailureHandler = authenticationFailureHandler;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.passwordEncoder = passwordEncoder;
        this.feheadUserServiceProperties = feheadUserServiceProperties;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String requestURI = request.getRequestURI();

        if (StringUtils.equals(authenticateUri, requestURI) && HttpMethod.POST == HttpMethod.resolve(request.getMethod())) { // 进行授权处理

            String username = request.getParameter(feheadUserServiceProperties.getAuthenticate().getUsernameParameter());
            String principal = request.getParameter(feheadUserServiceProperties.getAuthenticate().getPrincipalParameter());

            if(StringUtils.isEmpty(username)||StringUtils.isEmpty(principal)){
                authenticationFailureHandler.onAuthenticationFailure(request, response, new AuthenticationException(EmBusinessError.PARAMETER_VALIDATION_ERROR));
                return;
            }

            UserAccessAuthenticationToken requestUserAuthenticationToken;
            try {
                // 从数据库查询用户信息
                requestUserAuthenticationToken = userService.loadUser(username);
                // 密码校验
                if (passwordEncoder.matches(principal, requestUserAuthenticationToken.getCredentials())) { // 校验成功
                    // 构造成功token
                    UserAccessAuthenticationToken accessAuthenticationToken
                            = new UserAccessAuthenticationToken(
                            new AuthenticationDetails(new Date().getTime(), request.getRemoteAddr())
                            , true, new AuthenticationPrincipal(requestUserAuthenticationToken.getName(), "", true, true)
                            , ""
                            , requestUserAuthenticationToken.getName());
                    // 校验成功处理器
                    authenticationSuccessHandler.onAuthenticationSuccess(request, response, accessAuthenticationToken);
                } else { // 密码错误
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
        filterChain.doFilter(request, response);

    }
}
