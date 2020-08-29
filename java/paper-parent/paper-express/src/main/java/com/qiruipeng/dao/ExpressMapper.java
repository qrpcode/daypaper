package com.qiruipeng.dao;

import com.qiruipeng.pojo.Express;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

public interface ExpressMapper extends Mapper<Express> {

    @Insert("insert into express (userId, expressKey, itemType) VALUES (#{userId}, #{expressKey}, 1)")
    int insertNewExpress(Integer userId, String expressKey);

    @Update("update express set userName = #{userName},userPhone = #{userPhone}, userAddress = #{userAddress} where expressKey = #{expressKey}")
    void updateExpress(String expressKey, String userName, String userAddress, String userPhone);
}
