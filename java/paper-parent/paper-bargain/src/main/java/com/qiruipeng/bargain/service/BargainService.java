package com.qiruipeng.bargain.service;

import com.qiruipeng.bargain.pojo.BargainReturn;
import com.qiruipeng.bargain.pojo.NewBargainReturn;

public interface BargainService {
    NewBargainReturn newBargain(Integer userId, String itemType);

    BargainReturn getBargain(Integer userId, Integer id);
}
