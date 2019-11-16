package com.fehead.open.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fehead.lang.controller.BaseController;
import com.fehead.lang.error.AuthenticationException;
import com.fehead.lang.error.BusinessException;
import com.fehead.lang.error.EmBusinessError;
import com.fehead.lang.response.CommonReturnType;
import com.fehead.lang.response.FeheadResponse;
import com.fehead.open.user.controller.vo.UserVO;
import com.fehead.open.user.dao.UserInfoDetailDO;
import com.fehead.open.user.domain.UserInfoDetailModel;
import com.fehead.open.user.security.FeheadSecurityContext;
import com.fehead.open.user.security.authentication.Authentication;
import com.fehead.open.user.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

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

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    private final FeheadSecurityContext feheadSecurityContext;


    /**
     * 添加用户
     *
     * @param userVO
     * @return
     * @throws BusinessException
     */
    @Transactional(rollbackFor=Exception.class) // 所有异常均回滚
    @PostMapping("")
    @ApiOperation("用户注册接口")
    public CommonReturnType addUser(@ApiParam("user注册视图") @RequestBody UserVO userVO) throws BusinessException, IOException {

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

        // 返回注册成功
        return CommonReturnType.create("添加成功");
    }

    /**
     * 获取用户基本信息
     * @return
     * @throws AuthenticationException
     * @throws BusinessException
     */
    @GetMapping("")
    public FeheadResponse getUserInfoBasic() throws AuthenticationException, BusinessException {

        Authentication authentication = feheadSecurityContext.getAuthentication();

        if(authentication==null){ // 未授权用户
            throw new AuthenticationException(EmBusinessError.SERVICE_AUTHENTICATION_INVALID);
        }

        UserInfoDetailModel userInfoDetailModel = userService.getUserDetailInfo(authentication.getName());

        return CommonReturnType.create(userInfoDetailModel);
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
