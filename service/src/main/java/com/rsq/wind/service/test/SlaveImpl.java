package com.rsq.wind.service.test;

import com.rsq.wind.cache.RedisCacheImpl;
import com.rsq.wind.pojo.Users;
import com.rsq.wind.serviceinterface.test.Slave;
import com.rsq.wind.slave.dao.SlaveDao;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Author: shaoqing
 * date-time: 2020-10-18 12:22
 **/
@DubboService
public class SlaveImpl implements Slave {
    @Autowired
    SlaveDao slaveDao;
    @Resource
    RedisCacheImpl redisCacheImpl;

    @Override
    public Users test(Users u) {
      System.out.println(u.getUserId());
        Users us;
        try {
        us =(Users) redisCacheImpl.get(u.getUserId());
        System.out.println("缓存数据 "+us);
        // 双重检查锁，避免缓存击穿
        if (us==null){
            System.out.println(Thread.currentThread().getName()+" 尝试获取锁");
            if(redisCacheImpl.tryLock()){// 获取锁并从数据库获取数据并加入缓存
                System.out.println(Thread.currentThread().getName()+" 加锁");
                if (us==null){
                    List<Users> test = slaveDao.test(u);
                    redisCacheImpl.setCache(u.getUserId(),test.get(0));
                    return test.get(0);
                }
            }else {
                Thread.sleep(1);
                test(u);
            }

        }
      }catch (InterruptedException e){
          System.out.println(e);
          return null;
      } finally{
            if (redisCacheImpl!=null && redisCacheImpl.isLocked()){
                redisCacheImpl.unlock();
                System.out.println(Thread.currentThread().getName()+" 释放锁");
            }

      }
        return us;
    }
}
