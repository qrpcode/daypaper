package com.qiruipeng.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BuyMapper {

    @Select("select sum(memberGet) from buy WHERE userId = #{userId}")
    Integer getOrderWait(int userId);
}
