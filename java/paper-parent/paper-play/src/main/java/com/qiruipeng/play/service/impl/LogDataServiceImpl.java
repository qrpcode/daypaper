package com.qiruipeng.play.service.impl;

//import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONArray;
import com.qiruipeng.play.dao.LogDataMapper;
import com.qiruipeng.play.dao.MemberMapper;
import com.qiruipeng.play.dao.SystemMapper;
import com.qiruipeng.play.pojo.LogData;
import com.qiruipeng.play.pojo.LogReturn;
import com.qiruipeng.play.pojo.Member;
import com.qiruipeng.sys.pojo.TextSet;
import com.qiruipeng.play.service.LogDataService;
import com.qiruipeng.sys.service.SystemService;
import com.qiruipeng.play.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
public class LogDataServiceImpl implements LogDataService {

    private String today;

    private TextSet textSet;

    private final String SYSTEM_KEY = "system";

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private LogDataMapper logDataMapper;

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private SystemMapper systemMapper;

    /*@Reference
    private SystemService systemService;*/

    @Override
    @Transactional  //事务控制
    public LogReturn getGuess(Integer userId) {
        //给用户的水数量
        TextSet system = getSystem();
        int addNum = (int) (system.getGetGuessMin() + Math.random() * (system.getGetGuessMax() - system.getGetGuessMin() + 1));
        //加水事务控制
        return setUserGet(userId, "guess", addNum);
    }

    @Override
    @Transactional
    public LogReturn getMeal(Integer userId) {
        //给用户的水数量
        TextSet system = getSystem();
        int addNum = system.getGetMeal();
        //当前时间判断
        Calendar c = Calendar.getInstance();
        int h = c.get(Calendar.HOUR_OF_DAY); //时
        String type = "meal";
        if(h < 8 && h >= 6){
            type = "meal1";
        }
        if(h < 14 && h >= 12){
            type = "meal2";
        }
        if(h < 20 && h >= 18){
            type = "meal3";
        }
        if(!"meal".equals(type)) {
            return setUserGet(userId, type, addNum);
        }else{
            return new LogReturn(false);
        }
    }

    @Override
    @Transactional
    public LogReturn getStore(Integer userId) {
        //给用户的水数量
        TextSet system = getSystem();
        int addNum = system.getGetStore();
        //加水事务控制
        return setUserGet(userId, "store", addNum);
    }

    @Override
    @Transactional
    public LogReturn getVegetable(Integer userId) {
        //给用户的水数量
        TextSet system = getSystem();
        int addNum = system.getGetVegetable();
        //加水事务控制
        return setUserGet(userId, "vegetable", addNum);
    }

    @Override
    public LogReturn getPdd(Integer userId) {
        //加水事务控制
        return setUserGet(userId, "pdd", 100);
    }

    @Override
    @Transactional
    public LogReturn getFortune(Integer userId) {
        //如果是第一次和最后一次获得纸浆
        int logTime = logDataMapper.getLogDataNum(userId, getToday(), "fortune");
        if(logTime == 0 || logTime == 4){
            LogData logData = new LogData(userId, "fortune", new Date());
            logDataMapper.insertSelective(logData);
            //给用户的水数量
            TextSet system = getSystem();
            int getFortune100 = system.getGetFortune100();
            int getFortune300 = system.getGetFortune300();
            int getFortune466 = system.getGetFortune466();
            int getNum = (int) (0 + Math.random() * (100 + 1));
            int getFortune = 0;
            if(getNum <= getFortune100){
                getFortune = 100;
            }else if(getNum <= (getFortune100 + getFortune300)){
                getFortune = 300;
            }else if(getNum <= (getFortune100 + getFortune300 + getFortune466)){
                getFortune = 466;
            }else{
                getFortune = 600;
            }
            //给用户加水
            return addUserWater(userId, getFortune, logData.getId());
        }else{
            if(logTime < 5){
                //不管用户这次抽奖是否获得奖品都添加记录
                LogData logData = new LogData(userId, "fortune", new Date());
                logDataMapper.insertSelective(logData);
                //fail值为1 展示广告
                return new LogReturn(0, 0, 0, 1);
            }else{
                return new LogReturn(false);
            }
        }
    }

    /**
     * 连续签到
     * @param userId 用户id
     * @return 返回结果
     */
    @Override
    @Transactional
    public LogReturn getSign(Integer userId) {
        //查询用户本周已经签到多少次
        int week = getWeek() - 1;
        int logDayNum = logDataMapper.getLogDataNearlyNum(userId, week);
        int[] getLogin = new int[]{600, 700, 800, 900, 1000, 1100, 1200};
        return setUserGet(userId, "login", getLogin[logDayNum]);
    }


    /**
     * 获取当前是星期几
     *
     * @return 返回星期几，1-7
     */
    private int getWeek() {
        Calendar calendar;
        calendar = Calendar.getInstance();
        System.out.println(calendar);
        int week = calendar.get(calendar.DAY_OF_WEEK) - 1;
        if (week == 0) {
            week = 7;
        }
        return week;
    }

    @Override
    @Transactional
    public LogReturn videoGet(Integer userId) {
        //给用户的水数量
        TextSet system = getSystem();
        int addNum = system.getGetVideo();
        //不限次数
        LogData logData = new LogData(userId, "video", new Date());
        logDataMapper.insertSelective(logData);
        //加奖励
        return addUserWater(userId, addNum, logData.getId());
    }

    /**
     * 用户活动加纸浆抽取方法
     * @param userId 用户id
     * @param helpType 活动类型
     * @param addNum 加水数量
     * @return 返回LogReturn对象，可直接返回
     */
    @Transactional
    public LogReturn setUserGet(Integer userId, String helpType, Integer addNum){
        if(logDataMapper.getLogDataNum(userId, getToday(), helpType) == 0){
            //添加领水记录
            LogData logData = new LogData(userId, helpType, new Date());
            logDataMapper.insertSelective(logData);
            return addUserWater(userId, addNum, logData.getId());
        }else{
            return new LogReturn(false);
        }
    }

    public LogReturn addUserWater(Integer userId, Integer addNum, Integer logDataId){
        //给用户加水
        Member member = memberMapper.selectByPrimaryKey(userId);
        member.setNumWater(member.getNumWater() + addNum);
        memberMapper.updateByPrimaryKey(member);
        //返回信息
        LogReturn logReturn = new LogReturn(true);
        logReturn.setId(logDataId);
        logReturn.setAddWater(addNum);
        logReturn.setNumWater(member.getNumWater());
        return logReturn;
    }

    /**
     * 抽取的一个获取当前日期的方法
     * @return 返回yyyy-MM-dd 格式日期
     */
    private String getToday(){
        if(today == null || !(today.length() > 1)){
            Date now = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            today = dateFormat.format(now);
        }
        return today;
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
                textSet = systemService.getSystem();
            }
        }
        return textSet;
    }
}
