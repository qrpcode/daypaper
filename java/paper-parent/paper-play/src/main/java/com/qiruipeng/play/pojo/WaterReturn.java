package com.qiruipeng.play.pojo;

public class WaterReturn {
    private Integer numWater;
    private String numPercentage;
    private String expressKey;
    private Integer fail;

    public WaterReturn() {
    }

    public WaterReturn(Integer numWater, String numPercentage, String expressKey, Integer fail) {
        this.numWater = numWater;
        this.numPercentage = numPercentage;
        this.expressKey = expressKey;
        this.fail = fail;
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

    public String getExpressKey() {
        return expressKey;
    }

    public void setExpressKey(String expressKey) {
        this.expressKey = expressKey;
    }

    public Integer getFail() {
        return fail;
    }

    public void setFail(Integer fail) {
        this.fail = fail;
    }
}
