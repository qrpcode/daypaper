package com.qiruipeng.play.controller;

import com.qiruipeng.play.pojo.LogReturn;
import com.qiruipeng.play.pojo.WaterReturn;
import com.qiruipeng.play.service.WaterService;
import com.qiruipeng.play.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/water")
public class WaterController {

    @Autowired
    private WaterService waterService;

    @PostMapping("/waterPaper")
    public WaterReturn waterPaper(@RequestParam("token") String token){
        Integer userId = JwtUtil.parseJwtRS256(token);
        if (userId != null) {
            return waterService.waterPaper(userId);
        } else {
            return null;
        }
    }
}
