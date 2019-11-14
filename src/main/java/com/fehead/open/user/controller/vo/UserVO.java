package com.fehead.open.user.controller.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author lmwis
 * @Date 2019-11-14 20:46
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {

    /**
     * username {Require}
     */
    private String username;

    /**
     * tel
     */
    private String tel;

    /**
     * password
     */
    private String password;

    /**
     * email
     */
    private String email;

    /**
     * 手机号或者邮箱的校验码
     */
    private String authenticateCode;

    /**
     * 注册模式：
     *  byTel
     *  byEmail
     */
    private String mode;
}
