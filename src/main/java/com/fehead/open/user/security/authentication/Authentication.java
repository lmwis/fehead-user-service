package com.fehead.open.user.security.authentication;


/**
 * @Description:
 * @Author lmwis
 * @Date 2019-11-15 21:22
 * @Version 1.0
 */
public interface Authentication {

    boolean isAuthenticated();

    AuthenticationDetails getDetails();

    AuthenticationPrincipal getPrincipal();

    String getCredentials();

    String getName();



}
