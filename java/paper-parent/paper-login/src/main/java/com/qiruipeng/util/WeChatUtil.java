package com.qiruipeng.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

public class WeChatUtil {

    private String appId = "*****";
    private String secret = "*****";

    @Autowired
    private RestTemplate restTemplate;

    public String codeGetOpen(String code){
        String accessTokenRequestUrl = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appId + "&secret=" + secret
                + "&js_code=" + code + "&grant_type=authorization_code";
        String getText = restTemplate.getForObject(accessTokenRequestUrl,String.class);
        //获取到token
        return JSONObject.parseObject(getText).getString("openid");
    }


}
