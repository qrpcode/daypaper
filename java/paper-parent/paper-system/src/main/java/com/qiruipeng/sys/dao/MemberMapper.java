package com.qiruipeng.sys.dao;

import com.qiruipeng.sys.pojo.Member;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

public interface MemberMapper extends Mapper<Member> {

    @Select("select count(*) from member where userPhone = #{userPhone}")
    int selectMemberPhoneNum(String userPhone);

    @Update("update member set numWater = numWater + #{addNum} where id = #{userId}")
    void addWater(Integer userId, Integer addNum);
}
