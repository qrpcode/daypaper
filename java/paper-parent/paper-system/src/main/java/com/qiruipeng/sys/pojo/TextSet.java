package com.qiruipeng.sys.pojo;

import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.DriverManager;

/**
 * @author Administrator
 */
@NameStyle(Style.normal)
@Table(name="textset")
public class TextSet implements Serializable {
    @Id
    private Integer id;

    private Integer helpShare;
    private Integer getPhone;
    private Integer getShare;
    private Integer getMeal;
    private Integer getInvite;
    private Integer getMammon;
    private Integer getVegetable;
    private Integer getCard;
    private Integer getStore;
    private Integer getVideo;
    private Integer getGuessMin;
    private Integer getGuessMax;
    private Integer getFortune100;
    private Integer getFortune300;
    private Integer getFortune466;
    private Integer getFortune600;
    private String getLogin;
    private String waterNum;
    private String paperMoney;
    private String expressMoney;
    private Boolean adItemHidden;
    private Boolean adPinHidden;
    private String adItemId;
    private String adItemImg;
    private String adPinImg;
    private String net;
    private String papertips;
    private String radiotext;
    private String linetext1;
    private String linetext2;
    private String pushtext;
    private String pushtips;
    private String task1title;
    private String task1tips;
    private String task2title;
    private String task2tips;
    private String task3title;
    private String task3tips1;
    private String task3tips2;
    private String task4title;
    private String task4tips;
    private String task5title;
    private String task5tips;
    private String task6title;
    private String task6tips;
    private String task7title;
    private String task7tips;
    private String task8title;
    private String task8tips;
    private String task9title;
    private String task9tips;
    private String task11title;
    private String task11tips;
    private String task12title;
    private String task12tips;
    private String bargaintips1;
    private String item1name;
    private String item1price;
    private String item1text;
    private String item1button;
    private String item2name;
    private String item2price;
    private String item2text;
    private String item2button;
    private String bargaintips2;
    private String bargaintips3;
    private String sharetips;
    private String mammontext;
    private String mammontips;
    private String mammonbutton;
    private Boolean noticehidden10;
    private String noticetext10;
    private String rule1;
    private String rule2;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHelpShare() {
        return helpShare;
    }

    public void setHelpShare(Integer helpShare) {
        this.helpShare = helpShare;
    }

    public Integer getGetPhone() {
        return getPhone;
    }

    public void setGetPhone(Integer getPhone) {
        this.getPhone = getPhone;
    }

    public Integer getGetShare() {
        return getShare;
    }

    public void setGetShare(Integer getShare) {
        this.getShare = getShare;
    }

    public Integer getGetMeal() {
        return getMeal;
    }

    public void setGetMeal(Integer getMeal) {
        this.getMeal = getMeal;
    }

    public Integer getGetInvite() {
        return getInvite;
    }

    public void setGetInvite(Integer getInvite) {
        this.getInvite = getInvite;
    }

    public Integer getGetMammon() {
        return getMammon;
    }

    public void setGetMammon(Integer getMammon) {
        this.getMammon = getMammon;
    }

    public Integer getGetVegetable() {
        return getVegetable;
    }

    public void setGetVegetable(Integer getVegetable) {
        this.getVegetable = getVegetable;
    }

    public Integer getGetCard() {
        return getCard;
    }

    public void setGetCard(Integer getCard) {
        this.getCard = getCard;
    }

    public Integer getGetStore() {
        return getStore;
    }

    public void setGetStore(Integer getStore) {
        this.getStore = getStore;
    }

    public Integer getGetVideo() {
        return getVideo;
    }

    public void setGetVideo(Integer getVideo) {
        this.getVideo = getVideo;
    }

    public Integer getGetGuessMin() {
        return getGuessMin;
    }

    public void setGetGuessMin(Integer getGuessMin) {
        this.getGuessMin = getGuessMin;
    }

    public Integer getGetGuessMax() {
        return getGuessMax;
    }

    public void setGetGuessMax(Integer getGuessMax) {
        this.getGuessMax = getGuessMax;
    }

    public Integer getGetFortune100() {
        return getFortune100;
    }

    public void setGetFortune100(Integer getFortune100) {
        this.getFortune100 = getFortune100;
    }

    public Integer getGetFortune300() {
        return getFortune300;
    }

    public void setGetFortune300(Integer getFortune300) {
        this.getFortune300 = getFortune300;
    }

    public Integer getGetFortune466() {
        return getFortune466;
    }

    public void setGetFortune466(Integer getFortune466) {
        this.getFortune466 = getFortune466;
    }

    public Integer getGetFortune600() {
        return getFortune600;
    }

    public void setGetFortune600(Integer getFortune600) {
        this.getFortune600 = getFortune600;
    }

    public String getGetLogin() {
        return getLogin;
    }

    public void setGetLogin(String getLogin) {
        this.getLogin = getLogin;
    }

    public String getWaterNum() {
        return waterNum;
    }

    public void setWaterNum(String waterNum) {
        this.waterNum = waterNum;
    }

    public String getPaperMoney() {
        return paperMoney;
    }

    public void setPaperMoney(String paperMoney) {
        this.paperMoney = paperMoney;
    }

    public String getExpressMoney() {
        return expressMoney;
    }

    public void setExpressMoney(String expressMoney) {
        this.expressMoney = expressMoney;
    }

    public Boolean getAdItemHidden() {
        return adItemHidden;
    }

    public void setAdItemHidden(Boolean adItemHidden) {
        this.adItemHidden = adItemHidden;
    }

    public Boolean getAdPinHidden() {
        return adPinHidden;
    }

    public void setAdPinHidden(Boolean adPinHidden) {
        this.adPinHidden = adPinHidden;
    }

    public String getAdItemId() {
        return adItemId;
    }

    public void setAdItemId(String adItemId) {
        this.adItemId = adItemId;
    }

    public String getAdItemImg() {
        return adItemImg;
    }

    public void setAdItemImg(String adItemImg) {
        this.adItemImg = adItemImg;
    }

    public String getAdPinImg() {
        return adPinImg;
    }

    public void setAdPinImg(String adPinImg) {
        this.adPinImg = adPinImg;
    }

    public String getNet() {
        return net;
    }

    public void setNet(String net) {
        this.net = net;
    }

    public String getPapertips() {
        return papertips;
    }

    public void setPapertips(String papertips) {
        this.papertips = papertips;
    }

    public String getRadiotext() {
        return radiotext;
    }

    public void setRadiotext(String radiotext) {
        this.radiotext = radiotext;
    }

    public String getLinetext1() {
        return linetext1;
    }

    public void setLinetext1(String linetext1) {
        this.linetext1 = linetext1;
    }

    public String getLinetext2() {
        return linetext2;
    }

    public void setLinetext2(String linetext2) {
        this.linetext2 = linetext2;
    }

    public String getPushtext() {
        return pushtext;
    }

    public void setPushtext(String pushtext) {
        this.pushtext = pushtext;
    }

    public String getPushtips() {
        return pushtips;
    }

    public void setPushtips(String pushtips) {
        this.pushtips = pushtips;
    }

    public String getTask1title() {
        return task1title;
    }

    public void setTask1title(String task1title) {
        this.task1title = task1title;
    }

    public String getTask1tips() {
        return task1tips;
    }

    public void setTask1tips(String task1tips) {
        this.task1tips = task1tips;
    }

    public String getTask2title() {
        return task2title;
    }

    public void setTask2title(String task2title) {
        this.task2title = task2title;
    }

    public String getTask2tips() {
        return task2tips;
    }

    public void setTask2tips(String task2tips) {
        this.task2tips = task2tips;
    }

    public String getTask3title() {
        return task3title;
    }

    public void setTask3title(String task3title) {
        this.task3title = task3title;
    }

    public String getTask3tips1() {
        return task3tips1;
    }

    public void setTask3tips1(String task3tips1) {
        this.task3tips1 = task3tips1;
    }

    public String getTask3tips2() {
        return task3tips2;
    }

    public void setTask3tips2(String task3tips2) {
        this.task3tips2 = task3tips2;
    }

    public String getTask4title() {
        return task4title;
    }

    public void setTask4title(String task4title) {
        this.task4title = task4title;
    }

    public String getTask4tips() {
        return task4tips;
    }

    public void setTask4tips(String task4tips) {
        this.task4tips = task4tips;
    }

    public String getTask5title() {
        return task5title;
    }

    public void setTask5title(String task5title) {
        this.task5title = task5title;
    }

    public String getTask5tips() {
        return task5tips;
    }

    public void setTask5tips(String task5tips) {
        this.task5tips = task5tips;
    }

    public String getTask6title() {
        return task6title;
    }

    public void setTask6title(String task6title) {
        this.task6title = task6title;
    }

    public String getTask6tips() {
        return task6tips;
    }

    public void setTask6tips(String task6tips) {
        this.task6tips = task6tips;
    }

    public String getTask7title() {
        return task7title;
    }

    public void setTask7title(String task7title) {
        this.task7title = task7title;
    }

    public String getTask7tips() {
        return task7tips;
    }

    public void setTask7tips(String task7tips) {
        this.task7tips = task7tips;
    }

    public String getTask8title() {
        return task8title;
    }

    public void setTask8title(String task8title) {
        this.task8title = task8title;
    }

    public String getTask8tips() {
        return task8tips;
    }

    public void setTask8tips(String task8tips) {
        this.task8tips = task8tips;
    }

    public String getTask9title() {
        return task9title;
    }

    public void setTask9title(String task9title) {
        this.task9title = task9title;
    }

    public String getTask9tips() {
        return task9tips;
    }

    public void setTask9tips(String task9tips) {
        this.task9tips = task9tips;
    }

    public String getTask11title() {
        return task11title;
    }

    public void setTask11title(String task11title) {
        this.task11title = task11title;
    }

    public String getTask11tips() {
        return task11tips;
    }

    public void setTask11tips(String task11tips) {
        this.task11tips = task11tips;
    }

    public String getTask12title() {
        return task12title;
    }

    public void setTask12title(String task12title) {
        this.task12title = task12title;
    }

    public String getTask12tips() {
        return task12tips;
    }

    public void setTask12tips(String task12tips) {
        this.task12tips = task12tips;
    }

    public String getBargaintips1() {
        return bargaintips1;
    }

    public void setBargaintips1(String bargaintips1) {
        this.bargaintips1 = bargaintips1;
    }

    public String getItem1name() {
        return item1name;
    }

    public void setItem1name(String item1name) {
        this.item1name = item1name;
    }

    public String getItem1price() {
        return item1price;
    }

    public void setItem1price(String item1price) {
        this.item1price = item1price;
    }

    public String getItem1text() {
        return item1text;
    }

    public void setItem1text(String item1text) {
        this.item1text = item1text;
    }

    public String getItem1button() {
        return item1button;
    }

    public void setItem1button(String item1button) {
        this.item1button = item1button;
    }

    public String getItem2name() {
        return item2name;
    }

    public void setItem2name(String item2name) {
        this.item2name = item2name;
    }

    public String getItem2price() {
        return item2price;
    }

    public void setItem2price(String item2price) {
        this.item2price = item2price;
    }

    public String getItem2text() {
        return item2text;
    }

    public void setItem2text(String item2text) {
        this.item2text = item2text;
    }

    public String getItem2button() {
        return item2button;
    }

    public void setItem2button(String item2button) {
        this.item2button = item2button;
    }

    public String getBargaintips2() {
        return bargaintips2;
    }

    public void setBargaintips2(String bargaintips2) {
        this.bargaintips2 = bargaintips2;
    }

    public String getBargaintips3() {
        return bargaintips3;
    }

    public void setBargaintips3(String bargaintips3) {
        this.bargaintips3 = bargaintips3;
    }

    public String getSharetips() {
        return sharetips;
    }

    public void setSharetips(String sharetips) {
        this.sharetips = sharetips;
    }

    public String getMammontext() {
        return mammontext;
    }

    public void setMammontext(String mammontext) {
        this.mammontext = mammontext;
    }

    public String getMammontips() {
        return mammontips;
    }

    public void setMammontips(String mammontips) {
        this.mammontips = mammontips;
    }

    public String getMammonbutton() {
        return mammonbutton;
    }

    public void setMammonbutton(String mammonbutton) {
        this.mammonbutton = mammonbutton;
    }

    public Boolean getNoticehidden10() {
        return noticehidden10;
    }

    public void setNoticehidden10(Boolean noticehidden10) {
        this.noticehidden10 = noticehidden10;
    }

    public String getNoticetext10() {
        return noticetext10;
    }

    public void setNoticetext10(String noticetext10) {
        this.noticetext10 = noticetext10;
    }

    public String getRule1() {
        return rule1;
    }

    public void setRule1(String rule1) {
        this.rule1 = rule1;
    }

    public String getRule2() {
        return rule2;
    }

    public void setRule2(String rule2) {
        this.rule2 = rule2;
    }
}
