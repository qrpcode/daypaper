package com.qiruipeng.mq.listener;

import com.qiruipeng.mq.dao.ExpressMapper;
import com.qiruipeng.mq.dao.MemberMapper;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Component
@RabbitListener(queues = "TestDirectQueue")
public class RabbitMQListener {

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private ExpressMapper expressMapper;

    @RabbitHandler
    @Transactional
    public void process(String message) {
        String[] msg = message.split(",");
        if("p".equals(msg[0])){
            try{
                int userId = Integer.parseInt(msg[1]);
                expressMapper.insertNewExpress(userId, msg[2]);
            }catch (Exception e){
                System.out.println("可能存在一个错误");
            }
        }else if("w".equals(msg[0])){
            try{
                int userId = Integer.parseInt(msg[1]);
                int addNum = Integer.parseInt(msg[2]);
                memberMapper.update(userId, addNum);
            }catch (Exception e){
                System.out.println("可能存在一个错误");
            }
        }
    }
}
