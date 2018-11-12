package com.pdd.blog.web.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author:liyangpeng
 * @date:2018/11/6 16:11
 */
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate redisTemplate;

    public void set(String key,Object value){
        redisTemplate.opsForValue().set(key,value);
    }

    public void set(String key,Object value,long time){
        set(key,value);
        redisTemplate.expire(key,time, TimeUnit.MILLISECONDS);
    }

    public String get(String key){
        return String.valueOf(redisTemplate.opsForValue().get(key));
    }

    public void delKey(String key){
        redisTemplate.delete(key);
    }
}
