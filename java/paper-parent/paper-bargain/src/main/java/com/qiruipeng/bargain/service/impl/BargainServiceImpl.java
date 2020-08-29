package com.qiruipeng.bargain.service.impl;

import com.qiruipeng.bargain.dao.BargainMapper;
import com.qiruipeng.bargain.pojo.Bargain;
import com.qiruipeng.bargain.pojo.BargainReturn;
import com.qiruipeng.bargain.pojo.NewBargainReturn;
import com.qiruipeng.bargain.service.BargainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BargainServiceImpl implements BargainService {

    @Autowired
    private BargainMapper bargainMapper;

    @Override
    public NewBargainReturn newBargain(Integer userId, String itemType) {
        NewBargainReturn newBargainReturn = new NewBargainReturn(true);
        if(bargainMapper.selectBargainNum(userId) == 0){
            double itemPrice, itemNow;
            int itemTypeInt = 2;
            if("1".equals(itemType)){
                itemPrice = 19.9;
                itemNow = 14;
                itemTypeInt = 1;
            }else{
                itemPrice = 54.9;
                itemNow = 45;
            }
            //封装提交信息参数并提交
            Bargain bargain = new Bargain();
            bargain.setUserId(userId);
            bargain.setItemType(itemTypeInt);
            bargain.setItemNow(itemNow);
            bargain.setItemPrice(itemPrice);
            bargain.setBargainTime(new Date());
            bargainMapper.insertSelective(bargain);
            //封装返回的信息结果集
            newBargainReturn.setId(bargain.getId());
            newBargainReturn.setItemNow(itemNow);
            return newBargainReturn;
        }else{
            return new NewBargainReturn(false);  //有未砍成订单
        }
    }

    @Override
    public BargainReturn getBargain(Integer userId, Integer id) {
        if(bargainMapper.selectNum(id) > 0){
            BargainReturn bargainReturn = new BargainReturn();
            Bargain bargain = bargainMapper.selectByPrimaryKey(id);
            if(bargain.getItemType() == 1){
                bargainReturn.setItemTitle("1提12卷家用原色卫生卷纸");
                bargainReturn.setItemImage("item12.png");
                bargainReturn.setLabel1("待发货");
                bargainReturn.setLabel2("正品保障");
                bargainReturn.setItemPrice(bargain.getItemPrice());
                bargainReturn.setItemNow(bargain.getItemNow());
                bargainReturn.setItemDown(bargain.getItemPrice() - bargain.getItemNow());
            }else{
                bargainReturn.setItemTitle("36卷实惠家用原色卫生卷纸");
                bargainReturn.setItemImage("item36.png");
                bargainReturn.setLabel1("待发货");
                bargainReturn.setLabel2("正品保障");
                bargainReturn.setItemPrice(bargain.getItemPrice());
                bargainReturn.setItemNow(bargain.getItemNow());
                bargainReturn.setItemDown(bargain.getItemPrice() - bargain.getItemNow());
            }
            bargainReturn.setLastTime((int)(((bargain.getBargainTime().getTime()/1000) + 84600) - (new Date().getTime()/1000)));
            bargainReturn.setFail(1);
            bargainReturn.setHelpList(bargainMapper.selectHelpList(bargain.getId()));
            return bargainReturn;
        }else{
            return new BargainReturn(false);
        }
    }
}
