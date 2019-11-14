package com.fehead.open.user.service;

import com.fehead.open.user.controller.vo.UserVO;

/**
 * @Description:
 * @Author lmwis
 * @Date 2019-11-14 21:00
 * @Version 1.0
 */
public interface UserService {


    void registerUserByEmail(UserVO userVO);

    void registerUser(UserVO userVO);

}
