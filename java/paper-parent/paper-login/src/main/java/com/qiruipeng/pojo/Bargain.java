package com.qiruipeng.pojo;


import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@NameStyle(Style.normal)
@Table(name="bargain")
public class Bargain {
    @Id
    private Integer id;
    private Integer userId;
    private Integer itemType;
    private Double itemPrice;
    private Double itemNow;
    private Date bargainTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getItemType() {
        return itemType;
    }

    public void setItemType(Integer itemType) {
        this.itemType = itemType;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Double getItemNow() {
        return itemNow;
    }

    public void setItemNow(Double itemNow) {
        this.itemNow = itemNow;
    }

    public Date getBargainTime() {
        return bargainTime;
    }

    public void setBargainTime(Date bargainTime) {
        this.bargainTime = bargainTime;
    }
}
