package com.rsq.wind.service.test;

import com.rsq.wind.pojo.Users;
import com.rsq.wind.serviceinterface.test.Slave;
import com.rsq.wind.slave.dao.SlaveDao;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Author: shaoqing
 * date-time: 2020-10-18 12:22
 **/
@DubboService
public class SlaveImpl implements Slave {
    @Autowired
    SlaveDao slaveDao;
    @Override
    public List<Users> test(Users u) {
      System.out.println(u.getUserName());
      return slaveDao.test(u);
    }
}
