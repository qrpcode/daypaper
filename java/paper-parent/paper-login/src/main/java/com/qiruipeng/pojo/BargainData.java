package com.qiruipeng.pojo;

import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@NameStyle(Style.normal)
@Table(name="bargaindata")
public class BargainData {
    @Id
    private Integer id;
    private Integer bargainId;
    private Integer userId;
    private Double downPrice;
    private Date bargainDay;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBargainId() {
        return bargainId;
    }

    public void setBargainId(Integer bargainId) {
        this.bargainId = bargainId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getDownPrice() {
        return downPrice;
    }

    public void setDownPrice(Double downPrice) {
        this.downPrice = downPrice;
    }

    public Date getBargainDay() {
        return bargainDay;
    }

    public void setBargainDay(Date bargainDay) {
        this.bargainDay = bargainDay;
    }
}
