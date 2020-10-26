package com.rsq.wind.service.test;

import com.rsq.wind.master.dao.MasterDao;
import com.rsq.wind.pojo.Users;
import com.rsq.wind.serviceinterface.test.Master;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Author: shaoqing
 * date-time: 2020-10-18 12:22
 **/
@DubboService
public class MasterImpl implements Master {
    @Autowired
    MasterDao masterDao;
    @Override
    public int delete(Users u) {
      System.out.println(u.getUserName());
      return masterDao.delete(u);
    }
}
