package com.qiruipeng.play.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

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
        //return redisTemplate.opsForValue().get(key);
        String s = redisTemplate.boundListOps("222").leftPop();

        return null;
    }


}
