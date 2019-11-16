package com.fehead.open.user.service;

import com.fehead.lang.error.AuthenticationException;
import com.fehead.lang.error.BusinessException;
import com.fehead.open.user.controller.vo.UserAuthentication;
import com.fehead.open.user.controller.vo.UserVO;
import com.fehead.open.user.security.authentication.UserAccessAuthenticationToken;

/**
 * @Description:
 * @Author lmwis
 * @Date 2019-11-14 21:00
 * @Version 1.0
 */
public interface UserService {

    void registerUser(UserVO userVO) throws BusinessException;

    UserAccessAuthenticationToken loadUser(String username) throws AuthenticationException;
}
