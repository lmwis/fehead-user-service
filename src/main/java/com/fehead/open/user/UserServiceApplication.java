package com.fehead.open.user;

import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.DiscoveryManager;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import sun.misc.Signal;

/**
 * @Description:
 * @Author lmwis
 * @Date 2019-11-10 16:58
 * @Version 1.0
 */
@SpringBootApplication
@MapperScan({"com.fehead.open.user.dao"})
@EnableEurekaClient
public class UserServiceApplication {


    private static final Logger logger = LoggerFactory.getLogger(UserServiceApplication.class);

    public static void main(String[] args) {


        // 应用关闭钩子
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                // 从注册中心实例注册列表删除
                logger.info("This should completed shut down of DiscoveryClient");

            }
        });

        SpringApplication.run(UserServiceApplication.class,args);
    }
}
