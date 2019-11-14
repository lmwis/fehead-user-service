package com.fehead.open.user.service.impl;

import com.fehead.lang.componment.StringIdGenerator;
import com.fehead.open.user.controller.vo.UserVO;
import com.fehead.open.user.dao.PasswordDO;
import com.fehead.open.user.dao.UserInfoCoreDO;
import com.fehead.open.user.dao.mapper.PasswordInfoMapper;
import com.fehead.open.user.dao.mapper.UserInfoCoreMapper;
import com.fehead.open.user.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    private final PasswordEncoder passwordEncoder;

    private final PasswordInfoMapper passwordInfoMapper;

    private final UserInfoCoreMapper userInfoCoreMapper;

    private final StringIdGenerator stringIdGenerator;


    @Override
    public void registerUserByEmail(UserVO userVO) {

    }

    @Override
    public void registerUser(UserVO userVO) {

        PasswordDO passwordDO = new PasswordDO(){{
            setPasswordEncode(passwordEncoder.encode(userVO.getPassword()));
        }};

        // 写入数据库，生成id
        passwordInfoMapper.insert(passwordDO);

        UserInfoCoreDO userInfoCoreDO = new UserInfoCoreDO();

        userInfoCoreDO.setCreateTime(new Date().getTime()); // 创建时间
        userInfoCoreDO.setUpdateTime(new Date().getTime()); // 修改时间
        userInfoCoreDO.setUsername(userVO.getUsername()); // username
        userInfoCoreDO.setNickName(userVO.getUsername()); // 默认nickname与username一样
        userInfoCoreDO.setRegisterMode(userVO.getMode()); // 注册模式
        userInfoCoreDO.setUserEmail(userVO.getEmail()); // 邮箱
        userInfoCoreDO.setUserTel(userVO.getTel()); // 手机号
        userInfoCoreDO.setPasswordId(passwordDO.getId());
        userInfoCoreDO.setId(stringIdGenerator.generatorId()); // 生成id

        // 写入数据库
        userInfoCoreMapper.insert(userInfoCoreDO);

    }
}
