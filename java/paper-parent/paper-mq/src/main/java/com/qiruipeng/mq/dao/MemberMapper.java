package com.qiruipeng.mq.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MemberMapper{

    @Update("update member set numWater = numWater + #{addNum} where id = #{userId}")
    void update(Integer userId, Integer addNum);
}
