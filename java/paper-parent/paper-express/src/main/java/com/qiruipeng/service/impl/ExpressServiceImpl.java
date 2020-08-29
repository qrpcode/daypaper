package com.qiruipeng.service.impl;

import com.qiruipeng.dao.ExpressMapper;
import com.qiruipeng.service.ExpressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpressServiceImpl implements ExpressService {

    @Autowired
    private ExpressMapper expressMapper;

    @Override
    public void addExpress(Integer userId, String expressKey) {
        expressMapper.insertNewExpress(userId, expressKey);
    }

    @Override
    public void updateExpress(String expressKey, String userName, String userAddress, String userPhone) {
        expressMapper.updateExpress(expressKey, userName, userAddress, userPhone);
    }
}
