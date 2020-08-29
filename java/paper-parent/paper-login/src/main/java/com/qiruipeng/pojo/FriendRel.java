package com.qiruipeng.pojo;

import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Id;
import javax.persistence.Table;

@NameStyle(Style.normal)
@Table(name="friend")
public class FriendRel {
    @Id
    private Integer id;
    private Integer friendIn;
    private Integer friendOut;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFriendIn() {
        return friendIn;
    }

    public void setFriendIn(Integer friendIn) {
        this.friendIn = friendIn;
    }

    public Integer getFriendOut() {
        return friendOut;
    }

    public void setFriendOut(Integer friendOut) {
        this.friendOut = friendOut;
    }
}

