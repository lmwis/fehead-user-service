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
import org.springframework.web.bind.annotation.*;

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


    /**
     * 添加用户
     *  添加成功返回 登陆成功的用户授权信息与基本信息获取认证
     * @param userVO
     * @return
     * @throws BusinessException
     */
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

        // 返回登陆成功授权和用户基本信息
        return CommonReturnType.create("");
    }

    @GetMapping("/authentication")
    public FeheadResponse test1(){
        return CommonReturnType.create('1');
    }

    @GetMapping("/test2")
    public FeheadResponse test2(){
        return CommonReturnType.create('2');
    }

}
