package com.rsq.wind.serviceinterface.test;

/**
 * Author: shaoqing
 * date-time: 2020-10-29 21:01
 **/
public  interface  CacheRedis<T> {
    public void setCache(String k,T v);
    public void setCache(int k,T v);
    public T get(int k);
    public T get(String k);

}
