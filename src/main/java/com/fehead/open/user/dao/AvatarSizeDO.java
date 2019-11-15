package com.fehead.open.user.dao;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Description:
 * @Author lmwis
 * @Date 2019-11-14 22:35
 * @Version 1.0
 */
@TableName("avatar_size")
@Data
public class AvatarSizeDO {

    @TableId
    private String id;

    @TableField("avatar_64")
    private String avatar64;

    @TableField("avatar_32")
    private String avatar32;

}
