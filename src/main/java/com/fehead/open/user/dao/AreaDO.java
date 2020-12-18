package com.fehead.open.user.dao;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Description: 住址信息
 * @Author lmwis
 * @Date 2019-11-14 22:33
 * @Version 1.0
 */
@TableName("area_info")
@Data
public class AreaDO {

    /**
     * uuid
     */
    @TableId
    private String id;

    private String province;

    private String city;

    private String district;

}
