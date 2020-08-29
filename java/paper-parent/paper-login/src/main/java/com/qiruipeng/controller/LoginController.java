package com.qiruipeng.controller;

import com.qiruipeng.pojo.Member;
import com.qiruipeng.pojo.MemberReturn;
import com.qiruipeng.service.LoginService;
import com.qiruipeng.util.WeChatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/code")
    public MemberReturn getMember(@RequestParam("code") String code, @RequestParam("userAvatar") String userAvatar,
                                   @RequestParam("userName") String userName, @RequestParam("userSex") String userSex,
                                   @RequestParam("shareId") String shareId){
        return loginService.getMemberByCode(code, userAvatar, userName, userSex, shareId);
    }

    @PostMapping("/token")
    public MemberReturn getMemberByToken(@RequestParam("token") String token, @RequestParam("shareId") String shareId){
        return loginService.getMemberByToken(shareId, token);
    }
}
