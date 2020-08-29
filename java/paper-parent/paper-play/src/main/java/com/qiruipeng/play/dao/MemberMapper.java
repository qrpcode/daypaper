package com.qiruipeng.play.dao;

import com.qiruipeng.play.pojo.Member;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

public interface MemberMapper extends Mapper<Member> {

    @Update("update member set numPercentage = 120 where id = #{userId}")
    void updateUserClean(Integer userId);
}
