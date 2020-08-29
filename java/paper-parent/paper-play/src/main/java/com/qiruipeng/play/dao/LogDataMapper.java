package com.qiruipeng.play.dao;

import com.qiruipeng.play.pojo.LogData;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface LogDataMapper extends Mapper<LogData> {

    @Select("select COUNT(*) from logdata where userId = #{userId} and logDay = #{logDay} and logType = #{logType}")
    int getLogDataNum(int userId, String logDay, String logType);

    @Select("select count(*) from logdata where userId = #{userId} and logdata.logType = 'login' and DATE_SUB(CURDATE(), INTERVAL #{dayNum} DAY) <= date(logDay)")
    int getLogDataNearlyNum(int userId, int dayNum);
}
