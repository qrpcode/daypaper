package com.qiruipeng.seckill.pojo;

/**
 * 秒杀信息返回
 */
public class SeckillReturn {
    private Integer numWater;
    private Integer addWater;
    private String expressKey;
    //1：抢到手纸  2:抢到金币  -1:token验证失败  -2:啥都没抢到  -3:不在抢购时间
    private Integer fail;

    public SeckillReturn() {
        this.numWater = 0;
        this.addWater = 0;
        this.expressKey = "";
        this.fail = 0;
    }

    public SeckillReturn(Integer numWater, Integer addWater, String expressKey, Integer fail) {
        this.numWater = numWater;
        this.addWater = addWater;
        this.expressKey = expressKey;
        this.fail = fail;
    }

    public Integer getNumWater() {
        return numWater;
    }

    public void setNumWater(Integer numWater) {
        this.numWater = numWater;
    }

    public Integer getAddWater() {
        return addWater;
    }

    public void setAddWater(Integer addWater) {
        this.addWater = addWater;
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
