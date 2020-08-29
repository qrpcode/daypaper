package com.qiruipeng.play.pojo;

import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@NameStyle(Style.normal)
@Table(name = "logdata")
public class LogData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userId;
    private String logType;
    private Integer helpUser;
    private Date logDay;

    public LogData(){}

    public LogData(Integer userId, String logType, Date logDay) {
        this.userId = userId;
        this.logType = logType;
        this.logDay = logDay;
    }

    public LogData(Integer userId, String logType, Integer helpUser, Date logDay) {
        this.userId = userId;
        this.logType = logType;
        this.helpUser = helpUser;
        this.logDay = logDay;
    }

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

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public Integer getHelpUser() {
        return helpUser;
    }

    public void setHelpUser(Integer helpUser) {
        this.helpUser = helpUser;
    }

    public Date getLogDay() {
        return logDay;
    }

    public void setLogDay(Date logDay) {
        this.logDay = logDay;
    }
}
