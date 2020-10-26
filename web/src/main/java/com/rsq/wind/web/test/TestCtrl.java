package com.rsq.wind.web.test;

import com.rsq.wind.pojo.Users;
import com.rsq.wind.serviceinterface.test.Master;
import com.rsq.wind.serviceinterface.test.Slave;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Author: shaoqing
 * date-time: 2020-10-18 12:50
 **/
@RestController
@RequestMapping("TestCtrl")
public class TestCtrl {
    @DubboReference
    Slave slave ;
    @DubboReference
    Master master;
    @RequestMapping("slave")
    public List<Users> slave(Users u){
        return slave.test(u);
    }

    @RequestMapping("master")
    public int master(Users u){
        return master.delete(u);
    }
}
