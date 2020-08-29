package com.qiruipeng.bargain.controller;

import com.qiruipeng.bargain.pojo.BargainReturn;
import com.qiruipeng.bargain.pojo.NewBargainReturn;
import com.qiruipeng.bargain.service.BargainService;
import com.qiruipeng.bargain.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/bargain")
public class BargainController {

    @Autowired
    private BargainService bargainService;

    @PostMapping("/newBargain")
    private NewBargainReturn newBargain(@RequestParam("token") String token,
                                        @RequestParam("itemType") String itemType){
        Integer userId = JwtUtil.parseJwtRS256(token);
        if (userId != null) {
            return bargainService.newBargain(userId, itemType);
        } else {
            return new NewBargainReturn(false);
        }
    }

    @PostMapping("/getBargain")
    private BargainReturn getBargain(@RequestParam("token") String token,
                                     @RequestParam("id") Integer id){
        Integer userId = JwtUtil.parseJwtRS256(token);
        if (userId != null) {
            return bargainService.getBargain(userId, id);
        } else {
            return new BargainReturn(false);
        }
    }

}
