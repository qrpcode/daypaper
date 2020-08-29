package com.qiruipeng.dao;

import com.qiruipeng.pojo.Bargain;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface BargainMapper extends Mapper<Bargain>{

    @Select("select id, userId from bargain where userId = #{userId} and bargainTime >=(NOW() - interval 24 hour) and itemNow < itemPrice")
    Bargain getUserBargain(Integer userId);
}
