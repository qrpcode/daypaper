package com.qiruipeng.sys.pojo;

public class LogReturn {
    private Integer id;
    private Integer numWater;
    private Integer addWater;
    private Integer fail;

    public LogReturn(boolean success){
        if(success){
            this.fail = 0;
        }else{
            this.fail = -1;
        }
        id = 0;
        numWater = 0;
        addWater = 0;
    }

    public LogReturn(Integer id, Integer numWater, Integer addWater, Integer fail) {
        this.id = id;
        this.numWater = numWater;
        this.addWater = addWater;
        this.fail = fail;
    }

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

    public Integer getAddWater() {
        return addWater;
    }

    public void setAddWater(Integer addWater) {
        this.addWater = addWater;
    }

    public Integer getFail() {
        return fail;
    }

    public void setFail(Integer fail) {
        this.fail = fail;
    }
}
