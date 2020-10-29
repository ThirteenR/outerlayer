package com.rsq.wind.service;

import com.rsq.wind.config.MasterDataSource;
import com.rsq.wind.config.SlaveDataSource;
import com.rsq.wind.pojo.Users;
import com.rsq.wind.serviceinterface.test.Slave;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import javax.annotation.Resource;


/**
 * Author: shaoqing
 * date-time: 2020-10-29 23:23
 **/
@SpringBootTest
@EnableDubbo
@Import({MasterDataSource.class, SlaveDataSource.class})
@ComponentScan(value = "com.rsq.wind.cache")
class SlaveImplTest {
    @DubboReference
    Slave slave;
    @Test
    public void test01(){
        for (int i=0;i<10;i++){
                Users users = new Users();
                users.setUserId("7");
                slave.test(users);
        }


    }
}