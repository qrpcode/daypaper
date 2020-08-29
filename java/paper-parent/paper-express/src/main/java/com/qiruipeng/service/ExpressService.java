package com.qiruipeng.service;

public interface ExpressService {
    void addExpress(Integer userId, String expressKey);

    void updateExpress(String expressKey, String userName, String userAddress, String userPhone);
}
