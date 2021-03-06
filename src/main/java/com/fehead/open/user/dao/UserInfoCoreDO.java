package com.fehead.open.user.dao;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @Description: DB用户核心信息类
 * @Author lmwis
 * @Date 2019-11-14 21:04
 * @Version 1.0
 */
@Data
@TableName("user_info_core")
public class UserInfoCoreDO {

    /**
     * uuid
     */
    @TableId
    private String id;

    /**
     * password
     */
    @TableField("password_id")
    private long passwordId;

    /**
     * 用户名 login
     */
    @TableField("username")
    private String username;

    /**
     * email login
     */
    @TableField("user_email")
    private String userEmail;

    /**
     * tel login
     */
    @TableField("user_tel")
    private String userTel;

    /**
     * for show
     */
    @TableField("nick_name")
    private String nickName;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;

    /**
     * 注册模式，byEmail | byTel
     */
    @TableField("register_mode")
    private String registerMode;

    @TableField(exist = false)
    private PasswordDO passwordDO;

}
