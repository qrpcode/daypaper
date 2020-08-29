package com.qiruipeng.seckill.service.impl;

import com.alibaba.fastjson.JSON;
import com.qiruipeng.seckill.config.RabbitMQConfig;
import com.qiruipeng.seckill.dao.MemberMapper;
import com.qiruipeng.seckill.pojo.Member;
import com.qiruipeng.seckill.pojo.SeckillReturn;
import com.qiruipeng.seckill.service.SeckillService;
import com.qiruipeng.seckill.util.RedisUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SeckillServiceImpl implements SeckillService {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private MemberMapper memberMapper;

    //1. 注入RabbitTemplate
    @Autowired
    private RabbitTemplate rabbitTemplate;

    private final String SECKILL_USER = "seckillUser";

    /**
     * 抢【卷纸】名额
     *
     * 逻辑：
     * 用户卷纸数量大于等于10抢到名额直接获得真实卷纸一提（正常需要集12卷）
     * 卷纸名额没有了还能获得纸浆
     *
     * @param userId 用户id
     * @return 返回结果集
     */
    @Override
    @Transactional
    public SeckillReturn SeckillPaperGet(Integer userId) {
        String EXPRESS_KEY = "expressKey";
        String EXPRESS_HAVE = "expressHave";
        //先确定现在可能有号
        if("1".equals(redisUtil.getRedis(EXPRESS_HAVE))) {
            //确定用户首次参加活动
            if(!redisUtil.hashGet(SECKILL_USER, userId + "")) {
                //尝试队列取号
                //redis储存key常量
                String expressKey = redisUtil.listPop(EXPRESS_KEY);
                if (expressKey != null) {
                    //取号成功
                    //验证是不是确实有10卷纸
                    Member member = memberMapper.selectByPrimaryKey(userId);
                    if (member != null && Double.parseDouble(member.getNumPercentage()) >= 1000) {
                        //验证通过
                        //用户处理
                        redisUtil.putHash(SECKILL_USER, userId + "", "p");
                        //用户养殖状态归零
                        member.setNumPercentage("120");
                        memberMapper.updateByPrimaryKey(member);
                        //添加MQ
                        rabbitTemplate.convertAndSend("TestDirectExchange", "express", member.getId() + "," + expressKey);
                        //返回结果
                        return new SeckillReturn(member.getNumWater(), 0, expressKey, 1);
                    } else {
                        //验证失败
                        //如果号码标记是没号了需要改成有号
                        if ("1".equals(redisUtil.getRedis(EXPRESS_HAVE))) {
                            redisUtil.saveRedis(EXPRESS_HAVE, "0");
                        }
                        //归还号码
                        redisUtil.putList(EXPRESS_KEY, expressKey);
                        //账户不到10卷纸，领纸浆吧
                        return SeckillWaterGet(userId);
                    }
                } else {
                    //取号失败
                    //判断是不是没号了
                    if (redisUtil.listSize(EXPRESS_KEY) < 1) {
                        //确实没号了，设置有没有号标记为 1
                        redisUtil.saveRedis(EXPRESS_HAVE, "0");
                    }
                    //没号了，领纸浆吧
                    return SeckillWaterGet(userId);
                }
            }else{
                //这一轮抢过了
                return new SeckillReturn();
            }
        }else{
            //没号了，当前流程失败
            return SeckillWaterGet(userId);
        }
    }

    /**
     * 抢【纸浆】名额
     * @param userId 用户id
     * @return 返回秒杀结果
     */
    @Override
    @Transactional
    public SeckillReturn SeckillWaterGet(Integer userId) {
        String WATER_HAVE = "waterHave";
        String WATER_KEY = "waterKey";
        //先确定现在可能有号
        if("1".equals(redisUtil.getRedis(WATER_HAVE))) {
            //确定用户首次参加活动
            if(redisUtil.hashGet(SECKILL_USER, userId + "")){
                //当前流程失败
                return new SeckillReturn();
            }
            //用户处理
            redisUtil.putHash(SECKILL_USER, userId + "", "w");
            //尝试队列取号
            String water = redisUtil.listPop(WATER_KEY);
            if (water != null) {
                //取号成功
                Member member = memberMapper.selectByPrimaryKey(userId);
                if(member != null){
                    int waterNum = Integer.parseInt(water);
                    member.setNumWater(member.getNumWater() + waterNum);
                    //加水传递MQ
                    String sendMsg = "w," + member.getId() + "," + water;
                    rabbitTemplate.convertAndSend("TestDirectExchange","TestDirectRouting", sendMsg);
                    //返回结果
                    return new SeckillReturn(member.getNumWater(), waterNum, "", 1);
                }else{
                    //验证失败
                    //如果号码标记是没号了需要改成有号
                    if ("1".equals(redisUtil.getRedis(WATER_HAVE))) {
                        redisUtil.saveRedis(WATER_HAVE, "0");
                    }
                    //归还号码
                    redisUtil.putList(WATER_KEY, water);
                }
            }else{
                //取号失败
                //判断是不是没号了
                if (redisUtil.listSize(WATER_KEY) < 1) {
                    //确实没号了，设置有没有号标记为 0
                    redisUtil.saveRedis(WATER_HAVE, "0");
                }
            }
        }
        //当前流程失败
        return new SeckillReturn();
    }
}
