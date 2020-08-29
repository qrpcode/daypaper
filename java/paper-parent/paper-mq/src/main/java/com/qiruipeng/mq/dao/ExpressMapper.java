package com.qiruipeng.mq.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface ExpressMapper{

    @Insert("insert into express (userId, expressKey, itemType) VALUES (#{userId}, #{expressKey}, 1)")
    int insertNewExpress(Integer userId, String expressKey);
}
