package com.qiruipeng.seckill.pojo;

import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Id;
import javax.persistence.Table;

@NameStyle(Style.normal)
@Table(name = "member")
public class Member {
    @Id
    private Integer id;
    private Integer numWater;
    private String numPercentage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumWater() {
        return numWater;
    }

    public void setNumWater(Integer numWater) {
        this.numWater = numWater;
    }

    public String getNumPercentage() {
        return numPercentage;
    }

    public void setNumPercentage(String numPercentage) {
        this.numPercentage = numPercentage;
    }
}
