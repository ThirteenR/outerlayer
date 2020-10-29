package com.rsq.wind.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class WebApplicationTests {

    //    StringRedisTemplate作为RedisTemplate的子类，只支持KV为String的操作
    @Autowired
    private RedisTemplate<String,String> redisTemplate; //k-v都是对象的
    @Autowired
    private StringRedisTemplate stringRedisTemplate; //操作k-v都是字符串的



    @Test
    public void test01(){
        redisTemplate.opsForValue().set("myKey","myValue");
        System.out.println(redisTemplate.opsForValue().get("myKey"));
    }

    @Test
    public void test02(){
//		操作redis中的字符串
        stringRedisTemplate.opsForValue().append("test","hello world");
    }

}
