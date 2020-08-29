package com.qiruipeng.dao;

import com.qiruipeng.pojo.Express;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ExpressMapper{

    @Select("select userId, expressKey, itemType from express where userId = #{userId} and ISNULL(userName)")
    Express selectNullExpress(int userId);
}
