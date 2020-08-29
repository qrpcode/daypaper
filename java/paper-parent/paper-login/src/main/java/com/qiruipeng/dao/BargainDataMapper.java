package com.qiruipeng.dao;

import com.qiruipeng.pojo.BargainData;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface BargainDataMapper extends Mapper<BargainData> {

    @Select("select count(*) from bargaindata where userId = #{userId} and bargainDay = #{today}")
    int selectTodayNum(Integer userId, String today);
}
