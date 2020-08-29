package com.qiruipeng.sys.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * redis操作封装
 * @author 齐睿鹏
 */
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public boolean hasRedis(String key){
        if(key.length() > 0 && redisTemplate != null){
            return redisTemplate.hasKey(key);
        }else{
            return false;
        }
    }

    public String getRedis(String key){
        return redisTemplate.opsForValue().get(key);
    }

    public boolean saveRedis(String key, String value){
        try {
            redisTemplate.opsForValue().set(key, value);
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
