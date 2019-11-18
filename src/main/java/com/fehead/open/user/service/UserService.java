package com.fehead.open.user.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fehead.lang.error.AuthenticationException;
import com.fehead.lang.error.BusinessException;
import com.fehead.open.user.controller.vo.UserAuthentication;
import com.fehead.open.user.controller.vo.UserVO;
import com.fehead.open.user.dao.UserInfoDetailDO;
import com.fehead.open.user.domain.UserInfoDetailModel;
import com.fehead.open.user.security.authentication.Authentication;
import com.fehead.open.user.security.authentication.UserAccessAuthenticationToken;

import java.io.IOException;

/**
 * @Description:
 * @Author lmwis
 * @Date 2019-11-14 21:00
 * @Version 1.0
 */
public interface UserService {

    void registerUser(UserVO userVO) throws BusinessException, IOException;

    UserAccessAuthenticationToken loadUser(String username) throws AuthenticationException;

    UserInfoDetailModel getUserDetailInfo(Authentication authentication) throws BusinessException;

    void updatePasswordByTelCode(Authentication authentication,String code,String password) throws BusinessException, IOException;

    void updateUserNickName(Authentication authentication, String nickName);

    void updateUserGender(Authentication authentication, String genderCode);

    void updateUserBirthday(Authentication authentication, String birthday);
}
