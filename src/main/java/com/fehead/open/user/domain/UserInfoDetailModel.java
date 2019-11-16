package com.fehead.open.user.domain;

import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Author lmwis
 * @Date 2019-11-16 18:06
 * @Version 1.0
 */
@Data
public class UserInfoDetailModel {

    private String username;

    private String nickName;

    private int gender;

    private Date birthday;

    private String email;

    private String tel;

    private String registerMode;

    private String avatar64;

    private String avatar32;

    private String province;

    private String city;

    private String district;

}
