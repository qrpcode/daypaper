package com.qiruipeng.controller;

import com.qiruipeng.service.ExpressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/addExpress")
public class AddExpressController {

    @Autowired
    private ExpressService expressService;

    @PostMapping
    public void addExpress(@RequestParam("userId") Integer userId, @RequestParam("expressKey") String expressKey){
        expressService.addExpress(userId, expressKey);
    }
}
