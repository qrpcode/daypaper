package com.qiruipeng.seckill.controller;

import com.qiruipeng.seckill.pojo.SeckillReturn;
import com.qiruipeng.seckill.service.SeckillService;
import com.qiruipeng.seckill.service.SystemSeckillService;
import com.qiruipeng.seckill.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;

@RestController
@CrossOrigin
@RequestMapping("/seckill")
public class SeckillController {

    @Autowired
    private SeckillService seckillService;

    @PostMapping("/paper")
    public SeckillReturn SeckillPaperGet(@RequestParam("token") String token){
        Integer userId = JwtUtil.parseJwtRS256(token);
        if (userId != null && getMinute() < 30) {
            return seckillService.SeckillPaperGet(userId);
        } else {
            return new SeckillReturn();
        }
    }

    @PostMapping("/water")
    public SeckillReturn SeckillWaterGet(@RequestParam("token") String token){
        Integer userId = JwtUtil.parseJwtRS256(token);
        if (userId != null && getMinute() < 30) {
            return seckillService.SeckillWaterGet(userId);
        } else {
            return new SeckillReturn();
        }
    }

    private static int getMinute() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        return calendar.get(Calendar.MINUTE);
    }

}
