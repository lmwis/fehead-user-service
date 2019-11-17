package com.fehead.open.user.properties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author lmwis
 * @Date 2019-11-17 21:53
 * @Version 1.0
 */
@Configuration
@EnableConfigurationProperties(FeheadUserServiceProperties.class)
public class FeheadUserServicePropertiesStarter {
}
