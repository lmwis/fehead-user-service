package com.fehead.open.user.security.impl;

import com.fehead.open.user.security.authentication.Authentication;
import com.fehead.open.user.security.FeheadSecurityContext;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author lmwis
 * @Date 2019-11-15 22:15
 * @Version 1.0
 */
@Component
public class FeheadSecurityContextImpl implements FeheadSecurityContext {

    private Authentication authentication;

    @Override
    public Authentication getAuthentication() {
        return this.authentication;
    }

    @Override
    public void setAuthentication(Authentication authentication) {
        this.authentication = authentication;
    }
}
