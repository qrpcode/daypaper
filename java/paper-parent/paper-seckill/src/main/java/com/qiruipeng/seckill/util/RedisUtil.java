package com.qiruipeng.seckill.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
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

    public void putListCollection(String key, List<String> list){
        redisTemplate.opsForList().leftPushAll(key, list);
    }

    public void putList(String key, String value){
        redisTemplate.opsForList().leftPush(key, value);
    }

    public void del(String key){
        redisTemplate.delete(key);
    }

    public String listPop(String key){
        //阻断任何报错可能
        try{
            return redisTemplate.boundListOps(key).rightPop();
        }catch (Exception e){
            return null;
        }
    }

    public Long listSize(String key){
        return redisTemplate.boundListOps(key).size();
    }

    public boolean hashGet(String key, String hashKey){
        HashOperations opsForHash = redisTemplate.opsForHash();
        return opsForHash.hasKey(key, hashKey);
    }

    public void putHash(String key, String hashKey, String value){
        HashOperations opsForHash = redisTemplate.opsForHash();
        opsForHash.put(key, hashKey, value);
    }
}
