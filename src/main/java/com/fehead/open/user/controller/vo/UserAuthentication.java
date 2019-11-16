package com.fehead.open.user.controller.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Description:
 * @Author lmwis
 * @Date 2019-11-16 11:16
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthentication {

    @NotNull
    @NotBlank
    private String username;

    @NotNull
    @NotBlank
    private String principal;

}
