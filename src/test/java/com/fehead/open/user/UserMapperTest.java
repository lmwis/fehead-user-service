package com.fehead.open.user;

import com.fehead.open.user.dao.mapper.UserInfoCoreMapper;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description:
 * @Author lmwis
 * @Date 2019-11-13 20:42
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    DemoConpoment demoConpoment;

    @Test
    public void autowiredTest(){
        System.out.println("==");
        System.out.println(demoConpoment.demo);
    }

    @Autowired
    UserInfoCoreMapper userInfoCoreMapper;

    @Test
    public void whenSelectFromUserMapper(){
        userInfoCoreMapper.selectList(null).forEach(k->{
            System.out.println(new ReflectionToStringBuilder(k));
        });
    }
}
