package com.qiruipeng.play.service.impl;

//import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONArray;
import com.qiruipeng.play.dao.ExpressMapper;
import com.qiruipeng.play.dao.MemberMapper;
import com.qiruipeng.play.dao.SystemMapper;
import com.qiruipeng.play.feign.ExpressFeign;
import com.qiruipeng.play.pojo.Member;
import com.qiruipeng.sys.pojo.TextSet;
import com.qiruipeng.play.pojo.WaterReturn;
import com.qiruipeng.play.service.WaterService;
import com.qiruipeng.play.util.RandTextUtil;
import com.qiruipeng.play.util.RedisUtil;
import com.qiruipeng.sys.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;

@Service
public class WaterServiceImpl implements WaterService {

    @Autowired
    private ExpressFeign expressFeign;

    @Autowired
    private MemberMapper memberMapper;

    //@Reference
    //private SystemService systemService;

    @Autowired
    private SystemMapper systemMapper;

    @Autowired
    private RedisUtil redisUtil;

    private TextSet textSet;
    private final String SYSTEM_KEY = "system";

    /**
     * 用户浇水事件
     * @param userId 用户id
     * @return 返回值为浇水结果集
     */
    @Override
    @Transactional  //需要全局的事务控制
    public WaterReturn waterPaper(Integer userId) {
        //用户扣水
        Member member = memberMapper.selectByPrimaryKey(userId);
        member.setNumWater(member.getNumWater() - 600);
        if(member.getNumWater() >= 0) {
            //将字符串的形式转换数组
            int[] waterNum = new int[12];
            String arr = getSystem().getWaterNum();
            arr = arr.substring(1).replace("]", "");
            String[] split = arr.split(",");
            for (int i = 0; i < split.length; i++) {
                split[i] = split[i].trim();
                waterNum[i] = Integer.parseInt(split[i]);
            }
            //添加进度
            double numPercentage = Double.parseDouble(member.getNumPercentage());
            int numTerm = (int)Math.floor(numPercentage / 100);
            DecimalFormat df = new DecimalFormat("#.00");
            numPercentage = 100.00/waterNum[numTerm] + numPercentage;
            member.setNumPercentage(df.format(numPercentage));
            memberMapper.updateByPrimaryKey(member);
            //生成返回的对象
            WaterReturn waterReturn = new WaterReturn(member.getNumWater(), df.format(numPercentage), "", 1);
            //跨等级判断
            int newNumTerm = (int)Math.floor(numPercentage / 100);
            if(numTerm != newNumTerm){
                if(newNumTerm == 12){
                    //成熟
                    String expressKey = RandTextUtil.getRandomString(32);
                    //用户进度归零
                    memberMapper.updateUserClean(userId);
                    waterReturn.setNumPercentage("120.00");
                    //添加待发货
                    expressFeign.addExpress(userId, expressKey);
                    waterReturn.setExpressKey(expressKey);
                }
            }
            return waterReturn;
        }else{
            return new WaterReturn(0, "0", "", -1);
        }
    }


    /**
     * Dubbo获取系统设置
     * @return 返回系统设置结果
     */
    private TextSet getSystem(){
        if(textSet == null){
            if(redisUtil.hasRedis(SYSTEM_KEY)){
                textSet = JSONArray.parseObject(redisUtil.getRedis(SYSTEM_KEY), TextSet.class);
            }else {
                textSet = systemMapper.selectByPrimaryKey("1");
            }
        }
        return textSet;
    }
}
