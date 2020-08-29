package com.qiruipeng.controller;

import com.qiruipeng.service.ExpressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/updateExpress")
public class UpdateExpressController {
    @Autowired
    private ExpressService expressService;

    @PostMapping
    public void updateExpress(@RequestParam("expressKey") String expressKey,
                              @RequestParam("userName") String userName,
                              @RequestParam("userAddress") String userAddress,
                              @RequestParam("userPhone") String userPhone){
        expressService.updateExpress(expressKey, userName, userAddress, userPhone);
    }
}
