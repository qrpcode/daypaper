package com.qiruipeng.sys.service.impl;

import com.qiruipeng.sys.dao.MemberMapper;
import com.qiruipeng.sys.dao.SendMapper;
import com.qiruipeng.sys.pojo.LogReturn;
import com.qiruipeng.sys.pojo.Member;
import com.qiruipeng.sys.pojo.SendData;
import com.qiruipeng.sys.service.SendService;
import com.qiruipeng.sys.util.SendUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SendServiceImpl implements SendService {

    @Autowired
    private SendMapper sendMapper;

    @Autowired
    private MemberMapper memberMapper;

    private String today;

    /**
     * 用户开始绑定手机请求发送验证码
     * @param userId 用户的id
     * @param userPhone 用户申请的手机号
     * @return 返回响应结果
     */
    @Override
    @Transactional
    public Integer phoneSend(Integer userId, String userPhone) {
        Pattern pattern = Pattern.compile("^[1]\\d{10}$");
        if(pattern.matcher(userPhone).matches()){
            int todaySendNum = sendMapper.getTodaySendNum(userId, "%" + getToday() + "%");
            if(todaySendNum < 3){
                Member member = memberMapper.selectByPrimaryKey(userId);
                if(member.getUserPhone() == null){
                    if(memberMapper.selectMemberPhoneNum(userPhone) == 0){
                        if(sendMapper.selectSendPhoneNum(userId, userPhone, "%" + getToday() + "%") < 3){
                            //用户效验通过
                            int code = (int)(100000 + Math.random()*(999999 - 100000 + 1));
                            SendUtil.sendCode(userPhone, code + "");
                            //添加发信
                            SendData sendData = new SendData();
                            sendData.setUserId(userId);
                            sendData.setUserPhone(userPhone);
                            sendData.setUserNum(code);
                            sendData.setSendTime(new Date());
                            sendMapper.insert(sendData);
                            //返回成功
                            return 1;
                        }else{
                            return -5; //其他用户请求本号码次数过多，号码保护
                        }
                    }else{
                        return -3; //此手机已被占用
                    }
                }else{
                    return -2; //此账号已绑定手机
                }
            }else{
                return -1;  //今日发送次数过多
            }
        }else{
            return -7; //手机号格式不正确
        }
    }

    /**
     * 效验用户验证码是否正确，如果正确绑定手机
     * @param userId 用户id
     * @param code 传递验证码
     * @return 返回用户验证码验证结果
     */
    @Override
    @Transactional
    public LogReturn testCode(Integer userId, String code) {
        Pattern pattern = Pattern.compile("^\\d{6}$");
        if(pattern.matcher(code).matches()){
            SendData sendData = sendMapper.selectSendCode(userId, code);
            if(sendData != null){
                //获取用户信息
                Member member = memberMapper.selectByPrimaryKey(userId);
                //效验正确，给用户改电话信息
                member.setUserPhone(sendData.getUserPhone());
                //给用户加水
                member.setNumWater(member.getNumWater() + 30000);
                memberMapper.updateByPrimaryKey(member);
                return new LogReturn(1, member.getNumWater(), 30000, 1);
            }else{
                return new LogReturn(0, 0, 0, -2);
            }
        }else{
            return new LogReturn(false);
        }
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
}
