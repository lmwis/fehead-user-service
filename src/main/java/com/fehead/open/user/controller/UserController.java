package com.fehead.open.user.controller;

import com.fehead.lang.controller.BaseController;
import com.fehead.lang.error.BusinessException;
import com.fehead.lang.error.EmBusinessError;
import com.fehead.lang.response.CommonReturnType;
import com.fehead.lang.response.FeheadResponse;
import com.fehead.open.user.controller.vo.UserVO;
import com.fehead.open.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    private static final String REGISTER_MODE_TEL = "ByTel";

    private static final String REGISTER_MODE_EMAIL = "ByEmail";

    private final UserService userService;


    @PostMapping("")
    public FeheadResponse addUser(@RequestBody UserVO userVO) throws BusinessException {


        if (this.validateNull(userVO)
                &&
                this.validateNull(userVO.getUsername()
                        , userVO.getAuthenticateCode()
                        , userVO.getMode())) { // 基本属性校验

            if (StringUtils.endsWithIgnoreCase(userVO.getMode(), REGISTER_MODE_EMAIL)) { // 邮箱注册

                userService.registerUserByEmail(userVO);
            } else if (StringUtils.endsWithIgnoreCase(userVO.getMode(), REGISTER_MODE_TEL)) { // 手机号码注册

                userService.registerUser(userVO);
            } else {
                throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "注册方式不合法");
            }

        }

        // 返回登陆成功状态
        return CommonReturnType.create("");

    }

}
