package com.fehead.open.user.security.authentication;

import com.fehead.lang.response.FeheadResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Description: 用户授权成功的token
 * @Author lmwis
 * @Date 2019-11-15 20:08
 * @Version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserAccessAuthenticationToken extends FeheadResponse implements Authentication{

    /**
     * 创建未校验的token
     * @param name
     * @param credentials
     */
    public UserAccessAuthenticationToken(String name,String credentials) {
        super();
        this.credentials = credentials;
        this.name = name;
        this.setAuthenticated(false);
    }

    /**
     * 创建校验成功的token
     * @param name
     * @param credentials
     */
    public UserAccessAuthenticationToken(AuthenticationDetails details, boolean authenticated, AuthenticationPrincipal principal, String credentials, String name) {
        this.details = details;
        this.authenticated = authenticated;
        this.principal = principal;
        this.credentials = credentials;
        this.name = name;
    }

    /**
     * createTime 认证成功时间
     * remoteAddress 认证机构ip
     */
    private AuthenticationDetails details;

    /**
     * 是否校验成功
     */
    private boolean authenticated;

    /**
     * username 认证用户名，可以是username,tel,email
     * password 密码 加密后的
     * enabled 是否可用
     * credentialsNonExpired 凭证是否过期
     */
    private AuthenticationPrincipal principal;

    /**
     * 凭证文本
     */
    private String credentials;

    /**
     * username
     */
    private String name;

    @Override
    public boolean isAuthenticated() {
        return this.authenticated;
    }

    @Override
    public AuthenticationDetails getDetails() {
        return this.details;
    }

    @Override
    public AuthenticationPrincipal getPrincipal() {
        return this.principal;
    }

    @Override
    public String getCredentials() {
        return this.credentials;
    }

    @Override
    public String getName() {
        return this.name;
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class AuthenticationDetails{

    private long createTime;

    private String remoteAddress;
}
@Data
@AllArgsConstructor
@NoArgsConstructor
class AuthenticationPrincipal{

    private String username;

    private String password;

    private boolean enabled;

    private boolean credentialsNonExpired;

}
