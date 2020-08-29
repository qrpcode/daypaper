package com.qiruipeng.play.service;

import com.qiruipeng.play.pojo.LogReturn;
import org.springframework.stereotype.Service;

public interface LogDataService {
    LogReturn getGuess(Integer userId);

    LogReturn videoGet(Integer userId);

    LogReturn getMeal(Integer userId);

    LogReturn getStore(Integer userId);

    LogReturn getVegetable(Integer userId);

    LogReturn getFortune(Integer userId);

    LogReturn getSign(Integer userId);

    LogReturn getPdd(Integer userId);
}
