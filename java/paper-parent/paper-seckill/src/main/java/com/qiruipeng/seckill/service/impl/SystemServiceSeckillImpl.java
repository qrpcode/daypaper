package com.qiruipeng.seckill.service.impl;

import com.qiruipeng.seckill.dao.SystemSeckillMapper;
import com.qiruipeng.seckill.pojo.SeckillSet;
import com.qiruipeng.seckill.service.SystemSeckillService;
import com.qiruipeng.seckill.util.RandTextUtil;
import com.qiruipeng.seckill.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;


@Service
public class SystemServiceSeckillImpl implements SystemSeckillService {

    @Autowired
    private SystemSeckillMapper systemSeckillMapper;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 数据预热
     */
    @Override
    public void SystemSeckill() {
        String EXPRESS_KEY = "expressKey";
        String WATER_KEY = "waterKey";
        String EXPRESS_HAVE = "expressHave";
        String WATER_HAVE = "waterHave";
        String SECKILL_USER = "seckillUser";
        //删除已有数据
        //redis储存key常量
        redisUtil.del(EXPRESS_KEY);
        redisUtil.del(WATER_KEY);
        redisUtil.del(SECKILL_USER);
        //设置当前有余量
        redisUtil.saveRedis(EXPRESS_HAVE, "1");
        redisUtil.saveRedis(WATER_HAVE, "1");
        //读取数据
        SeckillSet seckillSet = systemSeckillMapper.selectByPrimaryKey(1);
        //将系统读取数据缓存
        save(seckillSet);
        //生成缓存秘钥list，里面直接储存 expressKey
        List<String> express = new LinkedList<>();
        for (Integer integer = 0; integer < seckillSet.getPaperNum(); integer++) {
            express.add(RandTextUtil.getRandomString(32));
        }
        redisUtil.putListCollection(EXPRESS_KEY, express);
        //设置金币redis
        int allNum = seckillSet.getWaterNum() / seckillSet.getWaterNumOne();
        List<String> water = new LinkedList<>();
        for (int i = 0; i < allNum; i = i + 2) {
            int about = (int)(0 + Math.random()*(seckillSet.getWaterNumAbout()+1));
            water.add((seckillSet.getWaterNumOne() + about) + "");
            water.add((seckillSet.getWaterNumOne() - about) + "");
        }
        redisUtil.putListCollection(WATER_KEY, water);
    }

    @Override
    public SeckillSet getSeckill() {
        if(redisUtil.hasRedis("beginTime")){
            return new SeckillSet(Integer.parseInt(redisUtil.getRedis("beginTime")),
                    Integer.parseInt(redisUtil.getRedis("endTime")),
                    redisUtil.getRedis("String icoUrl"),
                    redisUtil.getRedis("waitText"),
                    redisUtil.getRedis("failText"),
                    redisUtil.getRedis("wrongText"),
                    Integer.parseInt(redisUtil.getRedis("maxLong")));
        }else{
            SeckillSet seckillSet = systemSeckillMapper.selectByPrimaryKey(1);
            save(seckillSet);
            return seckillSet;
        }
    }

    private void save(SeckillSet seckillSet){
        //将系统读取数据缓存
        redisUtil.saveRedis("beginTime", seckillSet.getBeginTime() + "");
        redisUtil.saveRedis("endTime", seckillSet.getEndTime() + "");
        redisUtil.saveRedis("icoUrl", seckillSet.getIcoUrl() + "");
        redisUtil.saveRedis("waitText", seckillSet.getWaitText());
        redisUtil.saveRedis("failText", seckillSet.getFailText());
        redisUtil.saveRedis("wrongText", seckillSet.getWrongText());
        redisUtil.saveRedis("maxLong", seckillSet.getMaxLong() + "");
    }
}
