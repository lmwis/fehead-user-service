package com.fehead.open.user.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Description:
 * @Author lmwis
 * @Date 2019-11-14 21:15
 * @Version 1.0
 */
@Data
@TableName("password_info")
public class PasswordDO {

    /**
     * int 自增id
     */
    @TableId(type= IdType.AUTO)
    private int id;

    @TableField("password_encode")
    private String passwordEncode;
}
