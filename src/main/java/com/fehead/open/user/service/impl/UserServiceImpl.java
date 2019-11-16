package com.fehead.open.user.service.impl;

import com.fehead.lang.componment.StringIdGenerator;
import com.fehead.lang.error.AuthenticationException;
import com.fehead.lang.error.BusinessException;
import com.fehead.lang.error.EmBusinessError;
import com.fehead.open.user.controller.vo.UserVO;
import com.fehead.open.user.dao.*;
import com.fehead.open.user.dao.mapper.*;
import com.fehead.open.user.domain.UserInfoDetailModel;
import com.fehead.open.user.security.authentication.AuthenticationSuccessHandler;
import com.fehead.open.user.security.authentication.UserAccessAuthenticationToken;
import com.fehead.open.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Description:
 * @Author lmwis
 * @Date 2019-11-14 21:00
 * @Version 1.0
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final String REGISTER_MODE_TEL = "ByTel";

    private static final String REGISTER_MODE_EMAIL = "ByEmail";

    private final PasswordEncoder passwordEncoder;

    private final PasswordInfoMapper passwordInfoMapper;

    private final UserInfoCoreMapper userInfoCoreMapper;

    private final AreaMapper areaMapper;

    private final AvatarSizeMapper avatarSizeMapper;

    private final UserInfoDetailMapper userInfoDetailMapper;

    private final StringIdGenerator stringIdGenerator;

    private final AuthenticationSuccessHandler authenticationSuccessHandler;


    @Override
    public void registerUser(UserVO userVO) throws BusinessException {

        String authenticateCode = userVO.getAuthenticateCode();
        boolean flag = false; // 校验结果
        if (StringUtils.equalsIgnoreCase(userVO.getMode(), REGISTER_MODE_EMAIL)) { // 邮箱校验
            // 调用服务进行校验
        } else if (StringUtils.equalsIgnoreCase(userVO.getMode(), REGISTER_MODE_TEL)) { // 手机校验
            // 调用服务进行校验

            flag = true;
        } else {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "注册方式不合法");
        }

        if (!flag) { // 校验失败
            throw new BusinessException(EmBusinessError.SMS_ILLEGAL, "用户认证信息无效");
        }
        // 注册流程

        // 不能重复注册
        UserInfoCoreDO existUserInfo = userInfoCoreMapper.selectByUsername(userVO.getUsername());
        if(existUserInfo!=null){
            throw new BusinessException(EmBusinessError.USER_ALREAY_EXIST);
        }

        PasswordDO passwordDO = new PasswordDO();

        passwordDO.setPasswordEncode(passwordEncoder.encode(userVO.getPassword()));

        // 写入数据库，生成id
        passwordInfoMapper.insert(passwordDO);

        UserInfoCoreDO userInfoCoreDO = new UserInfoCoreDO();
        userInfoCoreDO.setCreateTime(new Date()); // 创建时间
        userInfoCoreDO.setUpdateTime(new Date()); // 修改时间
        userInfoCoreDO.setUsername(userVO.getUsername()); // username
        userInfoCoreDO.setNickName(userVO.getUsername()); // 默认nickname与username一样
        userInfoCoreDO.setRegisterMode(userVO.getMode()); // 注册模式
        userInfoCoreDO.setUserEmail(userVO.getEmail()); // 邮箱
        userInfoCoreDO.setUserTel(userVO.getTel()); // 手机号
        userInfoCoreDO.setPasswordId(passwordDO.getId());
        userInfoCoreDO.setId(stringIdGenerator.generatorId()); // 生成id

        // 写入数据库
        userInfoCoreMapper.insert(userInfoCoreDO);

        // 用户基本信息补充 保持数据一致性
        AreaDO areaDO = new AreaDO();
        areaDO.setId(stringIdGenerator.generatorId());
        areaMapper.insert(areaDO);

        AvatarSizeDO avatarSizeDO = new AvatarSizeDO();
        avatarSizeDO.setId(stringIdGenerator.generatorId());
        avatarSizeMapper.insert(avatarSizeDO);

        UserInfoDetailDO userInfoDetailDO = new UserInfoDetailDO();
        userInfoDetailDO.setUsername(userInfoCoreDO.getUsername());
        userInfoDetailDO.setId(stringIdGenerator.generatorId());
        userInfoDetailDO.setUserAreaId(areaDO.getId());
        userInfoDetailDO.setUserAvatarId(avatarSizeDO.getId());
        userInfoDetailMapper.insert(userInfoDetailDO);



    }

    @Override
    public UserAccessAuthenticationToken loadUser(String username) throws AuthenticationException {

        UserInfoCoreDO userInfoCoreDO = userInfoCoreMapper.selectByUsername(username);

        if(userInfoCoreDO==null){
            throw new AuthenticationException(EmBusinessError.USER_NOT_EXIST);
        }

        // return user detail
        return new UserAccessAuthenticationToken(userInfoCoreDO.getUsername(),userInfoCoreDO.getPasswordDO().getPasswordEncode());
    }

    @Override
    public UserInfoDetailModel getUserDetailInfo(String name) throws BusinessException {

        UserInfoDetailDO userInfoDetailDO = userInfoDetailMapper.selectByUsername(name);

        if(userInfoDetailDO==null){
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
        }

        return convertFromUserInfoDetailDO(userInfoDetailDO);
    }

    private UserInfoDetailModel convertFromUserInfoDetailDO(UserInfoDetailDO userInfoDetailDO){
        if (userInfoDetailDO==null){
            return null;
        }

        UserInfoDetailModel userInfoDetailModel = new UserInfoDetailModel();

        userInfoDetailModel.setUsername(userInfoDetailDO.getUsername());
        userInfoDetailModel.setNickName(userInfoDetailDO.getUserInfoCoreDO().getNickName());
        userInfoDetailModel.setGender(userInfoDetailDO.getUserGender());
        userInfoDetailModel.setBirthday(userInfoDetailDO.getUserBirthday());
        userInfoDetailModel.setEmail(userInfoDetailDO.getUserInfoCoreDO().getUserEmail());
        userInfoDetailModel.setTel(userInfoDetailDO.getUserInfoCoreDO().getUserTel());
        userInfoDetailModel.setRegisterMode(userInfoDetailDO.getUserInfoCoreDO().getRegisterMode());
        userInfoDetailModel.setAvatar64(userInfoDetailDO.getAvatarSizeDO().getAvatar64());
        userInfoDetailModel.setAvatar32(userInfoDetailDO.getAvatarSizeDO().getAvatar32());
        userInfoDetailModel.setProvince(userInfoDetailDO.getAreaDO().getProvince());
        userInfoDetailModel.setCity(userInfoDetailDO.getAreaDO().getCity());
        userInfoDetailModel.setDistrict(userInfoDetailDO.getAreaDO().getDistrict());

        return userInfoDetailModel;
    }
}
