package com.fehead.open.user.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description:
 * @Author lmwis
 * @Date 2019-11-17 21:53
 * @Version 1.0
 */
@Data
@ConfigurationProperties(prefix = "fehead.user-service")
public class FeheadUserServiceProperties {

    UserServiceAuthenticateProperties authenticate = new UserServiceAuthenticateProperties();
}
