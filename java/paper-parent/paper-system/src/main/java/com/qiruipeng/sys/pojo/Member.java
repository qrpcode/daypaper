package com.qiruipeng.sys.pojo;


import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Id;
import javax.persistence.Table;

@NameStyle(Style.normal)
@Table(name="member")
public class Member {
    @Id
    private Integer id;
    private String userPhone;
    private Integer numWater;

    public Integer getNumWater() {
        return numWater;
    }

    public void setNumWater(Integer numWater) {
        this.numWater = numWater;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", userPhone='" + userPhone + '\'' +
                ", numWater=" + numWater +
                '}';
    }
}
