package com.fehead.open.user.dao;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Description:
 * @Author lmwis
 * @Date 2019-11-14 21:04
 * @Version 1.0
 */
@Data
@TableName("user_info_core")
public class UserInfoCoreDO {

    @TableId
    private String id;

    @TableField("password_id")
    private long passwordId;

    @TableField("username")
    private String username;

    @TableField("user_email")
    private String userEmail;

    @TableField("user_tel")
    private String userTel;

    @TableField("nick_name")
    private String nickName;

    @TableField("create_time")
    private long createTime;

    @TableField("update_time")
    private long updateTime;

    @TableField("register_mode")
    private String registerMode;

}
