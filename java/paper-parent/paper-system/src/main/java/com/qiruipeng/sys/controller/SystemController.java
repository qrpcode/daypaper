package com.qiruipeng.sys.controller;

import com.qiruipeng.sys.pojo.TextSet;
import com.qiruipeng.sys.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/system")
@CrossOrigin
public class SystemController {

    @Autowired
    private SystemService systemService;

    @GetMapping
    public TextSet getSystem(){
        return systemService.getSystem();
    }
}
