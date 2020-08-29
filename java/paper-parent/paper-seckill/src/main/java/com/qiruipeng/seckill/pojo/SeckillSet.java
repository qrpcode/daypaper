package com.qiruipeng.seckill.pojo;

import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Id;
import javax.persistence.Table;

@NameStyle(Style.normal)
@Table(name = "seckillset")
public class SeckillSet {
    @Id
    private Integer id;
    private Integer beginTime;
    private Integer endTime;
    private String icoUrl;
    private String waitText;
    private String failText;
    private String wrongText;
    private Integer maxLong;

    //整体设置数据，不需要缓存
    private Integer paperNum;
    private Integer waterNum;
    private Integer waterNumOne;
    private Integer waterNumAbout;

    public SeckillSet() {
    }

    public SeckillSet(Integer beginTime, Integer endTime, String icoUrl, String waitText, String failText, String wrongText, Integer maxLong) {
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.icoUrl = icoUrl;
        this.waitText = waitText;
        this.failText = failText;
        this.wrongText = wrongText;
        this.maxLong = maxLong;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Integer beginTime) {
        this.beginTime = beginTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    public String getIcoUrl() {
        return icoUrl;
    }

    public void setIcoUrl(String icoUrl) {
        this.icoUrl = icoUrl;
    }

    public String getWaitText() {
        return waitText;
    }

    public void setWaitText(String waitText) {
        this.waitText = waitText;
    }

    public String getFailText() {
        return failText;
    }

    public void setFailText(String failText) {
        this.failText = failText;
    }

    public String getWrongText() {
        return wrongText;
    }

    public void setWrongText(String wrongText) {
        this.wrongText = wrongText;
    }

    public Integer getMaxLong() {
        return maxLong;
    }

    public void setMaxLong(Integer maxLong) {
        this.maxLong = maxLong;
    }

    public Integer getPaperNum() {
        return paperNum;
    }

    public void setPaperNum(Integer paperNum) {
        this.paperNum = paperNum;
    }

    public Integer getWaterNum() {
        return waterNum;
    }

    public void setWaterNum(Integer waterNum) {
        this.waterNum = waterNum;
    }

    public Integer getWaterNumOne() {
        return waterNumOne;
    }

    public void setWaterNumOne(Integer waterNumOne) {
        this.waterNumOne = waterNumOne;
    }

    public Integer getWaterNumAbout() {
        return waterNumAbout;
    }

    public void setWaterNumAbout(Integer waterNumAbout) {
        this.waterNumAbout = waterNumAbout;
    }
}
