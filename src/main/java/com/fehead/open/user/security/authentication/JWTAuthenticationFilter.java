package com.fehead.open.user.security.authentication;

import com.fehead.lang.error.AuthenticationException;
import com.fehead.lang.error.EmBusinessError;
import com.fehead.open.user.security.FeheadSecurityContext;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @Description: JWT认证过滤器
 * @Author lmwis
 * @Date 2019-11-15 20:36
 * @Version 1.0
 */
@Component
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JWTAuthenticationFilter.class);

    /**
     * 认证失败处理器
     */
    private final AuthenticationFailureHandler authenticationFailureHandler;
    /**
     * 全局认证容器
     */
    private final FeheadSecurityContext feheadSecurityContext;

    /**
     * 解析签名
     */
    public static final String SINGE_KEY = "fehead";

    /**
     * token请求头
     */
    public static final String authorizationHeader = "Authorization";

    /**
     * uri require认证列表
     */
    private static final Map<String, List<HttpMethod>> authenticatedUriList = new HashMap<String, List<HttpMethod>>() {{
        put("/user", Arrays.asList(HttpMethod.GET, HttpMethod.DELETE, HttpMethod.PUT));
    }};

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String requestURI = request.getRequestURI();

        logger.info(requestURI);
        if (isUriRequiredAuthenticated(request, request.getRequestURI())) { // 如果需要认证
            String header = request.getHeader(authorizationHeader); // 获取认证信息
            if (header == null || !header.startsWith("Bearer ")) { // 无请求头或者不以"Bearer "开头认为无效
                // 认证不通过处理
                authenticationFailureHandler
                        .onAuthenticationFailure(request
                                , response
                                , new AuthenticationException(EmBusinessError.SERVICE_AUTHENTICATION_ILLEGAL));
                return;
            }
            // 有效token
            UserAccessAuthenticationToken authentication = getAuthentication(request);
            // 校验成功放入容器
            feheadSecurityContext.setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);

    }

    /**
     * 判断uri是否需要认证
     *
     * @param request
     * @param uri
     * @return
     */
    private boolean isUriRequiredAuthenticated(HttpServletRequest request, String uri) {


        if(authenticatedUriList.keySet().equals(uri)){ // 先判断uri是否存在，再核对http method
            for (HttpMethod httpMethod : authenticatedUriList.get(uri)) {
                if (HttpMethod.resolve(request.getMethod()) == httpMethod) { // 匹配则需要认证
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 从token中解析用户认证信息
     *
     * @param request
     * @return
     */
    private UserAccessAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null) {
            // parse the token.
            String user = Jwts.parser()
                    .setSigningKey(SINGE_KEY)
                    .parseClaimsJws(token.replace("Bearer ", ""))
                    .getBody()
                    .getSubject();

            if (user != null) {
                return new UserAccessAuthenticationToken(user,"");
            }
            return null;
        }
        return null;
    }
}
