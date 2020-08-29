package com.qiruipeng.play.controller;

import com.qiruipeng.play.pojo.LogReturn;
import com.qiruipeng.play.service.LogDataService;
import com.qiruipeng.play.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/play")
public class LogDataController {

    @Autowired
    private LogDataService logDataService;

    @PostMapping("/getGuess")
    public LogReturn getGuess(@RequestParam("token") String token) {
        Integer userId = JwtUtil.parseJwtRS256(token);
        if (userId != null) {
            return logDataService.getGuess(userId);
        } else {
            return new LogReturn(false);
        }
    }

    @PostMapping("/getFortune")
    public LogReturn getFortune(@RequestParam("token") String token) {
        Integer userId = JwtUtil.parseJwtRS256(token);
        if (userId != null) {
            return logDataService.getFortune(userId);
        } else {
            return new LogReturn(false);
        }
    }

    @PostMapping("/getVegetable")
    public LogReturn getVegetable(@RequestParam("token") String token) {
        Integer userId = JwtUtil.parseJwtRS256(token);
        if (userId != null) {
            return logDataService.getVegetable(userId);
        } else {
            return new LogReturn(false);
        }
    }

    @PostMapping("/getPdd")
    public LogReturn getPdd(@RequestParam("token") String token) {
        Integer userId = JwtUtil.parseJwtRS256(token);
        if (userId != null) {
            return logDataService.getPdd(userId);
        } else {
            return new LogReturn(false);
        }
    }

    @PostMapping("/getStore")
    public LogReturn getStore(@RequestParam("token") String token) {
        Integer userId = JwtUtil.parseJwtRS256(token);
        if (userId != null) {
            return logDataService.getStore(userId);
        } else {
            return new LogReturn(false);
        }
    }

    @PostMapping("/getMeal")
    public LogReturn getMeal(@RequestParam("token") String token) {
        Integer userId = JwtUtil.parseJwtRS256(token);
        if (userId != null) {
            return logDataService.getMeal(userId);
        } else {
            return new LogReturn(false);
        }
    }

    @PostMapping("/getSign")
    public LogReturn getSign(@RequestParam("token") String token) {
        Integer userId = JwtUtil.parseJwtRS256(token);
        if (userId != null) {
            return logDataService.getSign(userId);
        } else {
            return new LogReturn(false);
        }
    }

    @PostMapping("/videoGet")
    public LogReturn videoGet(@RequestParam("token") String token) {
        Integer userId = JwtUtil.parseJwtRS256(token);
        if (userId != null) {
            return logDataService.videoGet(userId);
        } else {
            return new LogReturn(false);
        }
    }
}
