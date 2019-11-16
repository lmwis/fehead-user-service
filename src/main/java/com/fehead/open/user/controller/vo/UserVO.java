package com.fehead.open.user.controller.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    @NotBlank(message = "username不能为空")
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
    @NotBlank(message = "校验码不能为空")
    private String authenticateCode;

    /**
     * 注册模式：
     * byTel
     * byEmail
     */
    @NotBlank(message = "注册方式不能为空")
    private String mode;
}
