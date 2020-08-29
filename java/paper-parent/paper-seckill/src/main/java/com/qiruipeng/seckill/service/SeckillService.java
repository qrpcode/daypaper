package com.qiruipeng.seckill.service;

import com.qiruipeng.seckill.pojo.SeckillReturn;

public interface SeckillService {
    SeckillReturn SeckillPaperGet(Integer userId);

    SeckillReturn SeckillWaterGet(Integer userId);
}
