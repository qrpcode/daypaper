package com.qiruipeng.sys.service;

import com.qiruipeng.sys.pojo.LogReturn;

public interface SendService {
    Integer phoneSend(Integer userId, String userPhone);

    LogReturn testCode(Integer userId, String code);
}
