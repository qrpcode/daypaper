package com.qiruipeng.dao;

import com.qiruipeng.pojo.LogData;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface LogDataMapper extends Mapper<LogData> {

    @Select("select * from logdata where userId = #{userId} and logDay = #{today}")
    List<LogData> selectToday(int userId, String today);

    @Select("select count(*) from logdata where userId = #{userId} and logdata.logType = 'login' and DATE_SUB(CURDATE(), INTERVAL #{dayNum} DAY) <= date(logDay)")
    int getLogDataNearlyNum(int userId, int dayNum);

    @Select("select count(*) from logdata where userId = #{userId} and logdata.logType = 'share' and logDay = #{today}")
    int getShareNearlyNum(int userId, String today);

    @Select("select count(*) from logdata where userId = #{userId} and logdata.helpUser = #{helpUser} and logDay = #{today}")
    int getShareNum(int userId, Integer helpUser, String today);

    @Select("select count(*) from logdata where logdata.helpUser = #{helpUser} and logDay = #{today}")
    int getHelpNearlyNum(int helpUser, String today);
}
