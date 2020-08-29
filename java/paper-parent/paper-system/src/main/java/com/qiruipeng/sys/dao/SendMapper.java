package com.qiruipeng.sys.dao;

import com.qiruipeng.sys.pojo.SendData;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface SendMapper extends Mapper<SendData> {

    @Select("select COUNT(*) from senddata where userId = #{userId} and sendTime like #{today}")
    int getTodaySendNum(Integer userId, String today);

    @Select("select COUNT(*) from senddata where userPhone = #{userPhone} and sendTime like #{today} and userId != #{userId}")
    int selectSendPhoneNum(Integer userId, String userPhone, String today);

    @Select("select * from senddata where userId = #{userId} and userNum = #{code}")
    SendData selectSendCode(Integer userId, String code);
}
