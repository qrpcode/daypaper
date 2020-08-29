package com.qiruipeng.service;

import com.qiruipeng.pojo.Member;
import com.qiruipeng.pojo.MemberReturn;

public interface LoginService {

    MemberReturn getMemberByCode(String code, String userAvatar, String userName, String userSex, String shareId);

    MemberReturn getMemberByToken(String shareId, String jwt);
}
