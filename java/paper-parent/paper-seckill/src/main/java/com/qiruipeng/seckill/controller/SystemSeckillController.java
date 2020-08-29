package com.qiruipeng.seckill.controller;

import com.qiruipeng.seckill.pojo.SeckillSet;
import com.qiruipeng.seckill.service.SystemSeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 秒杀系统设置和提前数据库预热准备工作
 */
@RestController
@CrossOrigin
@RequestMapping("/seckillSet")
public class SystemSeckillController {

    @Autowired
    private SystemSeckillService systemSeckillService;

    @GetMapping("/hot")
    public void SystemSeckill(){
        systemSeckillService.SystemSeckill();
    }

    @PostMapping("/get")
    public SeckillSet getSeckill(){
        return systemSeckillService.getSeckill();
    }
}
