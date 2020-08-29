package com.qiruipeng.dao;

import com.qiruipeng.pojo.Friend;
import com.qiruipeng.pojo.Member;
import com.qiruipeng.pojo.SendData;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface LoginMapper extends Mapper<Member> {

    @Select("select * from senddata where userId = #{userId} and sendTime like #{sendTime} order by id desc limit 1")
    SendData selectLastOneByUserId(Integer userId, String sendTime);

    @Select("select member.id,member.userSex, member.userName, member.numWater, member.userAvatar from friend, member where (friend.friendIn = #{userId} and friend.friendOut = member.id) or (friend.friendOut = #{userId} and friend.friendIn = member.id)")
    List<Friend> selectFriendList(Integer userId);

}
