package com.qiruipeng.play.pojo;

import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@NameStyle(Style.normal)
@Table(name="member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
