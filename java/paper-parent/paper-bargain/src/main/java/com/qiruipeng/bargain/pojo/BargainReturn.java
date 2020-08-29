package com.qiruipeng.bargain.pojo;

import java.util.List;

public class BargainReturn {
    private String itemTitle;
    private String itemImage;
    private String label1;
    private String label2;
    private Double itemPrice;
    private Double itemNow;
    private Double itemDown;
    private List<Helper> helpList;
    private Integer lastTime;
    private Integer fail;

    public BargainReturn() {
    }

    public BargainReturn(boolean success) {
        fail = -1;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public String getLabel1() {
        return label1;
    }

    public void setLabel1(String label1) {
        this.label1 = label1;
    }

    public String getLabel2() {
        return label2;
    }

    public void setLabel2(String label2) {
        this.label2 = label2;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Double getItemNow() {
        return itemNow;
    }

    public void setItemNow(Double itemNow) {
        this.itemNow = itemNow;
    }

    public Double getItemDown() {
        return itemDown;
    }

    public void setItemDown(Double itemDown) {
        this.itemDown = itemDown;
    }

    public List<Helper> getHelpList() {
        return helpList;
    }

    public void setHelpList(List<Helper> helpList) {
        this.helpList = helpList;
    }

    public Integer getLastTime() {
        return lastTime;
    }

    public void setLastTime(Integer lastTime) {
        this.lastTime = lastTime;
    }

    public Integer getFail() {
        return fail;
    }

    public void setFail(Integer fail) {
        this.fail = fail;
    }
}
