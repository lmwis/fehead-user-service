package com.fehead.open.user.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Description: 用户头像
 * @Author lmwis
 * @Date 2019-11-14 22:35
 * @Version 1.0
 */
@TableName("avatar_size")
@Data
public class AvatarSizeDO {

    /**
     * int id
     */
    @TableId(type= IdType.AUTO)
    private int id;

    /**
     * url
     */
    @TableField("avatar_64")
    private String avatar64;
    /**
     * url
     */
    @TableField("avatar_32")
    private String avatar32;

}
