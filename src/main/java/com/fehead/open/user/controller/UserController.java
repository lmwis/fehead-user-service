package com.fehead.open.user.controller;

import com.fehead.lang.controller.BaseController;
import com.fehead.lang.error.BusinessException;
import com.fehead.lang.error.EmBusinessError;
import com.fehead.lang.response.CommonReturnType;
import com.fehead.lang.response.FeheadResponse;
import com.fehead.open.user.controller.vo.UserVO;
import com.fehead.open.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Description:
 * @Author lmwis
 * @Date 2019-11-14 20:36
 * @Version 1.0
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController extends BaseController {


    private final UserService userService;


    /**
     * 添加用户
     * 添加成功返回 登陆成功的用户授权信息与基本信息获取认证
     *
     * @param userVO
     * @return
     * @throws BusinessException
     */
    @Transactional(rollbackFor=Exception.class) // 所有异常均回滚
    @PostMapping("")
    public FeheadResponse addUser(@Valid @RequestBody UserVO userVO) throws BusinessException {


        if (this.validateNull(userVO)
                &&
                this.validateNull(userVO.getUsername()
                        , userVO.getAuthenticateCode()
                        , userVO.getPassword()
                        , userVO.getMode())) { // 基本属性校验
            // 注册流程
            userService.registerUser(userVO);

        }else {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        // 返回登陆成功授权和用户基本信息
        return CommonReturnType.create("注册成功");
    }

//    @GetMapping("/authentication")
//    public FeheadResponse authentication(@Valid @RequestBody UserAuthentication userAuthentication) {
//
//        // 创建未校验的token
//        UserAccessAuthenticationToken requestUserAuthenticationToken = new UserAccessAuthenticationToken(userAuthentication.getUsername(),userAuthentication.getPrincipal());
//
//        // 进行token校验
//        UserAccessAuthenticationToken userAccessAuthenticationToken = userService.authentication(requestUserAuthenticationToken);
//
//
//        return userAccessAuthenticationToken;
//    }

    @GetMapping("/test2")
    public FeheadResponse test2() {
        return CommonReturnType.create('2');
    }

}
