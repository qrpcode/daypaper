package com.qiruipeng.play.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "express")
@RequestMapping("/addExpress")
public interface ExpressFeign {

    @PostMapping
    public void addExpress(@RequestParam("userId") Integer userId, @RequestParam("expressKey") String expressKey);
}
