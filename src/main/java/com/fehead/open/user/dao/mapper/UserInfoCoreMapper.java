package com.fehead.open.user.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fehead.open.user.dao.UserInfoCoreDO;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.Date;

/**
 * @Description:
 * @Author lmwis
 * @Date 2019-11-14 21:17
 * @Version 1.0
 */
public interface UserInfoCoreMapper extends BaseMapper<UserInfoCoreDO> {

    @Results(id="userInfoCore",value = {
            @Result(id = true,column = "id",property = "id",jdbcType = JdbcType.VARCHAR,javaType = String.class),
            @Result(column = "username",property = "username",jdbcType = JdbcType.VARCHAR,javaType = String.class),
            @Result(column = "user_email",property = "userEmail",jdbcType = JdbcType.VARCHAR,javaType = String.class),
            @Result(column = "user_tel",property = "userTel",jdbcType = JdbcType.VARCHAR,javaType = String.class),
            @Result(column = "nick_name",property = "nickName",jdbcType = JdbcType.VARCHAR,javaType = String.class),
            @Result(column = "create_time",property = "createTime",jdbcType = JdbcType.DATE,javaType = Date.class),
            @Result(column = "update_time",property = "updateTime",jdbcType = JdbcType.DATE,javaType = Date.class),
            @Result(column = "register_mode",property = "registerMode",jdbcType = JdbcType.VARCHAR,javaType = String.class),
            @Result(column = "password_id",property = "passwordDO",one = @One(select = "com.fehead.open.user.dao.mapper.PasswordInfoMapper.selectById"))
    })

    @Select("select * from user_info_core where username = #{username}")
//    @ResultMap(value = "userInfoCore")
    public UserInfoCoreDO selectByUsername(String username);
}
