package com.qiruipeng.bargain.pojo;

public class NewBargainReturn {
    private Integer id;
    private Double itemNow;
    private Integer fail;

    public NewBargainReturn(boolean success){
        if(success){
            fail = 1;
        }else{
            id = 0;
            itemNow = 0.0;
            fail = -1;
        }
    }

    public NewBargainReturn(Integer id, Double itemNow, Integer fail) {
        this.id = id;
        this.itemNow = itemNow;
        this.fail = fail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getItemNow() {
        return itemNow;
    }

    public void setItemNow(Double itemNow) {
        this.itemNow = itemNow;
    }

    public Integer getFail() {
        return fail;
    }

    public void setFail(Integer fail) {
        this.fail = fail;
    }
}
