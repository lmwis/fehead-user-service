package com.fehead.open.user.security;

import com.fehead.open.user.security.authentication.Authentication;

/**
 * @Description:
 * @Author lmwis
 * @Date 2019-11-15 22:13
 * @Version 1.0
 */
public interface FeheadSecurityContext {

    public Authentication getAuthentication();

    public void setAuthentication(Authentication authentication);
}
