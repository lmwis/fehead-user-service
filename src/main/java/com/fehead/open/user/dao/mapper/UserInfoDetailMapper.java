package com.fehead.open.user.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fehead.open.user.dao.UserInfoCoreDO;
import com.fehead.open.user.dao.UserInfoDetailDO;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.Date;

/**
 * @Description:
 * @Author lmwis
 * @Date 2019-11-16 17:39
 * @Version 1.0
 */
public interface UserInfoDetailMapper extends BaseMapper<UserInfoDetailDO> {

    @Results(id="userInfoDetail",value = {
            @Result(id = true,column = "id",property = "id",jdbcType = JdbcType.VARCHAR,javaType = String.class),
            @Result(column = "username",property = "username",jdbcType = JdbcType.VARCHAR,javaType = String.class),
            @Result(column = "user_avatar_id",property = "userAvatarId",jdbcType = JdbcType.VARCHAR,javaType = String.class),
            @Result(column = "user_area_id",property = "userAreaId",jdbcType = JdbcType.VARCHAR,javaType = String.class),
            @Result(column = "gender",property = "userGender",jdbcType = JdbcType.TINYINT,javaType = Integer.class),
            @Result(column = "birthday",property = "userBirthday",jdbcType = JdbcType.DATE,javaType = Date.class),
            @Result(column = "username",property = "userInfoCoreDO",one = @One(select = "com.fehead.open.user.dao.mapper.UserInfoCoreMapper.selectByUsername")),
            @Result(column = "user_avatar_id",property = "avatarSizeDO",one = @One(select = "com.fehead.open.user.dao.mapper.AvatarSizeMapper.selectById")),
            @Result(column = "user_area_id",property = "areaDO",one = @One(select = "com.fehead.open.user.dao.mapper.AreaMapper.selectById"))
    })

    @Select("select * from user_info_detail where username = #{username}")
//    @ResultMap(value = "userInfoDetail")
    public UserInfoDetailDO selectByUsername(String username);
}
