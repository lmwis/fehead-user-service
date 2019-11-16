package com.fehead.open.user.dao;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @Description:
 * @Author lmwis
 * @Date 2019-11-16 17:32
 * @Version 1.0
 */
@TableName("user_info_detail")
@Data
public class UserInfoDetailDO {

    @TableId
    private String id;

    @TableField("username")
    private String username;

    @TableField("user_gender")
    private int userGender;

    @TableField("user_birthday")
    private Date userBirthday;

    @TableField("user_avatar_id")
    private String userAvatarId;

    @TableField("user_area_id")
    private String userAreaId;

    @TableField(exist = false)
    private UserInfoCoreDO userInfoCoreDO;

    @TableField(exist = false)
    private AvatarSizeDO avatarSizeDO;

    @TableField(exist = false)
    private AreaDO areaDO;



}
