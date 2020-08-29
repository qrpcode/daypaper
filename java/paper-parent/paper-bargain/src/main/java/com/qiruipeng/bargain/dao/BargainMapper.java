package com.qiruipeng.bargain.dao;

import com.qiruipeng.bargain.pojo.Bargain;
import com.qiruipeng.bargain.pojo.Helper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BargainMapper extends Mapper<Bargain> {

    @Select("select COUNT(*) from bargain where userId = #{userId} and bargainTime >=(NOW() - interval 24 hour)")
    int selectBargainNum(Integer userId);

    @Select("select COUNT(*) from bargain where id = #{id} and bargainTime >=(NOW() - interval 24 hour)")
    int selectNum(Integer id);

    @Select("select bargaindata.userId, bargaindata.downPrice, member.userName from bargaindata, member where bargaindata.userId = member.id and bargaindata.bargainId = #{bargainId}")
    List<Helper> selectHelpList(Integer bargainId);
}
