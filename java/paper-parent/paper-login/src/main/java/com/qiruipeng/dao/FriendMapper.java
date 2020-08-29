package com.qiruipeng.dao;

import com.qiruipeng.pojo.FriendRel;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface FriendMapper extends Mapper<FriendRel> {
    @Select("select COUNT(*) from friend where (friend.friendIn = #{userId} and friend.friendOut = #{shareUserId}) or (friend.friendOut = #{userId} and friend.friendIn = #{shareUserId})")
    int selectNumFriend(Integer userId, Integer shareUserId);
}
