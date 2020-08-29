package com.qiruipeng.pojo;

import java.util.List;

/**
 * 用户首次登陆需要返回的member对象
 */
public class MemberReturn {
    //用户对象
    private Member member;

    //是否是新用户
    private Boolean newUser;

    //手机验证码发送情况
    private Integer sendTime;
    private String maybePhone;
    private Integer sendHidden;

    //用户当日活动参与情况
    private Integer guess;
    private Integer fortune;
    private Integer fortuneNum;
    private Integer vegetable;
    private Integer meal1;
    private Integer meal2;
    private Integer meal3;
    private Integer card;
    private Integer store;
    private Integer share;
    private Integer invite;
    private Integer login;
    private Integer loginDay;

    //是否有快递
    private Integer express;
    private String expressKey;
    private String expressName;

    //获取砍价信息
    private Integer bargain;
    private Integer bargainId;

    //获取收货后即将获得卷纸数量
    private Integer paperWait;

    //用户好友列表
    private List<Friend> friendList;

    //用户jwt秘钥
    private String token;

    //好友互助
    private Integer shareReturn;
    private Integer shareAdd;
    private Integer bargainFail;
    private Double downPrice;

    public MemberReturn(Boolean newUser) {
        this.newUser = newUser;
        //手机验证码发送情况
        sendTime = 0;
        maybePhone = "";
        sendHidden = 0;

        //获取收货后即将获得卷纸数量
        paperWait = 0;

        //用户当日活动参与情况
        guess = 0;
        fortune = 0;
        fortuneNum = 0;
        vegetable = 0;
        meal1 = 0;
        meal2 = 0;
        meal3 = 0;
        card = 0;
        store = 0;
        share = 0;
        invite = 0;
        login = 0;
        loginDay = 0;

        //是否有快递
        express = 0;
        expressKey = "";
        expressName = "";

        //获取砍价信息
        bargain = 0;
        bargainId = 0;

        //用户好友列表
        friendList = null;

        //好友互助
        shareReturn = 0;
        shareAdd = 0;
        bargainFail = -3;
        downPrice = 0.0;
    }

    public Boolean getNewUser() {
        return newUser;
    }

    public Double getDownPrice() {
        return downPrice;
    }

    public void setDownPrice(Double downPrice) {
        this.downPrice = downPrice;
    }

    public Integer getBargainFail() {
        return bargainFail;
    }

    public void setBargainFail(Integer bargainFail) {
        this.bargainFail = bargainFail;
    }

    public Integer getShareReturn() {
        return shareReturn;
    }

    public void setShareReturn(Integer shareReturn) {
        this.shareReturn = shareReturn;
    }

    public Integer getShareAdd() {
        return shareAdd;
    }

    public void setShareAdd(Integer shareAdd) {
        this.shareAdd = shareAdd;
    }

    public void setNewUser(Boolean newUser) {
        this.newUser = newUser;
    }

    public Integer getPaperWait() {
        return paperWait;
    }

    public void setPaperWait(Integer paperWait) {
        this.paperWait = paperWait;
    }

    public Integer getBargain() {
        return bargain;
    }

    public void setBargain(Integer bargain) {
        this.bargain = bargain;
    }

    public Integer getBargainId() {
        return bargainId;
    }

    public void setBargainId(Integer bargainId) {
        this.bargainId = bargainId;
    }

    public Integer getLoginDay() {
        return loginDay;
    }

    public void setLoginDay(Integer loginDay) {
        this.loginDay = loginDay;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Integer getGuess() {
        return guess;
    }

    public void setGuess(Integer guess) {
        this.guess = guess;
    }

    public Integer getFortune() {
        return fortune;
    }

    public void setFortune(Integer fortune) {
        this.fortune = fortune;
    }

    public Integer getFortuneNum() {
        return fortuneNum;
    }

    public void setFortuneNum(Integer fortuneNum) {
        this.fortuneNum = fortuneNum;
    }

    public Integer getVegetable() {
        return vegetable;
    }

    public void setVegetable(Integer vegetable) {
        this.vegetable = vegetable;
    }

    public Integer getMeal1() {
        return meal1;
    }

    public void setMeal1(Integer meal1) {
        this.meal1 = meal1;
    }

    public Integer getMeal2() {
        return meal2;
    }

    public void setMeal2(Integer meal2) {
        this.meal2 = meal2;
    }

    public Integer getMeal3() {
        return meal3;
    }

    public void setMeal3(Integer meal3) {
        this.meal3 = meal3;
    }

    public Integer getCard() {
        return card;
    }

    public void setCard(Integer card) {
        this.card = card;
    }

    public Integer getStore() {
        return store;
    }

    public void setStore(Integer store) {
        this.store = store;
    }

    public Integer getShare() {
        return share;
    }

    public void setShare(Integer share) {
        this.share = share;
    }

    public Integer getInvite() {
        return invite;
    }

    public void setInvite(Integer invite) {
        this.invite = invite;
    }

    public Integer getLogin() {
        return login;
    }

    public void setLogin(Integer login) {
        this.login = login;
    }

    public Integer getSendTime() {
        return sendTime;
    }

    public void setSendTime(Integer sendTime) {
        this.sendTime = sendTime;
    }

    public String getMaybePhone() {
        return maybePhone;
    }

    public void setMaybePhone(String maybePhone) {
        this.maybePhone = maybePhone;
    }

    public Integer getSendHidden() {
        return sendHidden;
    }

    public void setSendHidden(Integer sendHidden) {
        this.sendHidden = sendHidden;
    }

    public Integer getExpress() {
        return express;
    }

    public void setExpress(Integer express) {
        this.express = express;
    }

    public String getExpressKey() {
        return expressKey;
    }

    public void setExpressKey(String expressKey) {
        this.expressKey = expressKey;
    }

    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    public List<Friend> getFriendList() {
        return friendList;
    }

    public void setFriendList(List<Friend> friendList) {
        this.friendList = friendList;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
