package com.rsq.wind.cache;

import com.rsq.wind.serviceinterface.test.CacheRedis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;


/**
 * Author: shaoqing
 * date-time: 2020-10-29 21:10
 **/
@Component
public class RedisCacheImpl extends ReentrantLock implements CacheRedis {
    @Autowired
    RedisTemplate redisTemplate;
    @Override
    public void setCache(String k, Object v) {
        ValueOperations<String, Object> vo = redisTemplate.opsForValue();
        if (!hasKey(k)){// 缓存命中，取出缓存数据
           vo.set(k,v,1, TimeUnit.MINUTES);
           System.out.println("添加缓存成功");
        }else {
            System.out.println("缓存已存在");
        }


    }

    @Override
    public void setCache(int k, Object v) {
        String key = "user_" + k;
        setCache(key,v);
    }

    @Override
    public Object get(int k) {
        String key = "user_" + k;
        return get(key);
    }

    @Override
    public Object get(String k) {
        ValueOperations<String, Object> vo = redisTemplate.opsForValue();
        if (hasKey(k)){
            return vo.get(k);
        }
        return null;
    }
    private boolean hasKey(String k){

//        判断缓存中是否存在此数据
        return redisTemplate.hasKey(k);
    }
}
