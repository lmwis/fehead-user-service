package com.fehead.open.user.properties;

import lombok.Data;

/**
 * @Description:
 * @Author lmwis
 * @Date 2019-11-17 21:58
 * @Version 1.0
 */
@Data
public class UserServiceAuthenticateProperties {

    private String usernameParameter = "username";

    private String principalParameter = "principal";

    private String authenticateUri = "/user/authenticate";

}
