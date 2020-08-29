package com.qiruipeng.sys.controller;

import com.qiruipeng.sys.pojo.LogReturn;
import com.qiruipeng.sys.service.SendService;
import com.qiruipeng.sys.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/send")
@CrossOrigin
public class SendController {

    @Autowired
    private SendService sendService;

    @PostMapping("/phoneSend")
    public Integer phoneSend(@RequestParam("token") String token,
                             @RequestParam("userPhone") String userPhone){
        Integer userId = JwtUtil.parseJwtRS256(token);
        if (userId != null) {
            return sendService.phoneSend(userId, userPhone);
        } else {
            return -1;
        }
    }

    @PostMapping("/testCode")
    public LogReturn testCode(@RequestParam("token") String token,
                             @RequestParam("code") String code){
        Integer userId = JwtUtil.parseJwtRS256(token);
        if (userId != null) {
            return sendService.testCode(userId, code);
        } else {
            return new LogReturn(false);
        }
    }
}
