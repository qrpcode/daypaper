package com.qiruipeng.service.impl;

import com.qiruipeng.dao.*;
import com.qiruipeng.pojo.*;
import com.qiruipeng.service.LoginService;
import com.qiruipeng.util.JwtUtil;
import com.qiruipeng.util.WeChatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;

    @Autowired
    private LogDataMapper logDataMapper;

    @Autowired
    private ExpressMapper expressMapper;

    @Autowired
    private BargainMapper bargainMapper;

    @Autowired
    private BargainDataMapper bargainDataMapper;

    @Autowired
    private FriendMapper friendMapper;

    @Autowired
    private BuyMapper buyMapper;

    @Autowired
    private WeChatUtil weChatUtil;

    private String today;

    @Override
    @Transactional
    public MemberReturn getMemberByCode(String code, String userAvatar, String userName, String userSex,
                                        String shareId) {
        String openId = weChatUtil.codeGetOpen(code);
        System.out.println(openId);
        if (openId != null && openId.length() > 10) {
            Member member = getMemberOpenId(openId);
            if (member != null) {
                return getMemberByMember(shareId, member);
            } else {
                //新用户最后再把member对象传递进去，老用户在获取用户信息之前就传递member对象
                MemberReturn memberReturn = new MemberReturn(true);

                //用户不存在创建新用户
                Member newMember = new Member();
                newMember.setOpenId(openId);
                newMember.setUserSex(Integer.parseInt(userSex));
                newMember.setUserName(userName);
                newMember.setUserAvatar(userAvatar);
                newMember.setTimeLog(new Date());
                newMember.setTimeReg(new Date());
                newMember.setNumWater(600);
                newMember.setNumPercentage(120);
                loginMapper.insertSelective(newMember);

                //获取短信验证码
                getSendData(memberReturn);
                //获取用户好友列表
                getFriendList(memberReturn);
                //获取用户当日参与活动
                getLogData(memberReturn);
                //获取快递信息
                getExpress(memberReturn);
                //获取砍价信息
                getBargain(memberReturn);
                //获取用户确认收货即将获得卷纸
                getPaperWait(memberReturn);
                //获取砍价信息
                getShare(shareId, memberReturn, newMember.getId());

                //设置返回对象中的member
                memberReturn.setMember(newMember);
                String token = JwtUtil.buildJwtRS256(newMember.getId(), 31536000);
                memberReturn.setToken(token);
                return memberReturn;
            }
        }
        return null;
    }

    @Override
    @Transactional
    public MemberReturn getMemberByToken(String shareId, String jwt) {
        Integer userId = JwtUtil.parseJwtRS256(jwt);
        if (userId != null) {
            Member member = loginMapper.selectByPrimaryKey(userId);
            if (member != null) {
                return getMemberByMember(shareId, member);
            }
        }
        return null;
    }

    /**
     * 根据openId查找member
     *
     * @param openId 用户的openid
     * @return 返回member对象
     */
    private Member getMemberOpenId(String openId) {
        Member member = new Member();
        member.setOpenId(openId);
        return loginMapper.selectOne(member);
    }

    /**
     * 根据用户信息获取用户数据返回结果集
     *
     * @param member 用户信息
     * @return 用户结果集
     */
    private MemberReturn getMemberByMember(String shareId, Member member) {
        MemberReturn memberReturn = new MemberReturn(false);
        //设置member对象
        memberReturn.setMember(member);
        //获取短信验证码
        getSendData(memberReturn);
        //获取用户好友列表
        getFriendList(memberReturn);
        //获取用户当日参与活动
        getLogData(memberReturn);
        //获取快递信息
        getExpress(memberReturn);
        //获取砍价信息
        getBargain(memberReturn);
        //获取用户确认收货即将获得卷纸
        getPaperWait(memberReturn);
        //获取砍价信息
        getShare(shareId, memberReturn, member.getId());
        try {
            String token = JwtUtil.buildJwtRS256(member.getId(), 31536000);
            memberReturn.setToken(token);
            return memberReturn;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 分享事件
     *
     * @param shareId      分享秘钥
     * @param memberReturn 返回结果集
     */
    private void getShare(String shareId, MemberReturn memberReturn, Integer fromUserId) {
        int shareNum = 0;
        String shareType = "";
        //判断逻辑
        if (shareId != null && shareId.length() > 1) {
            try {
                shareType = shareId.substring(0, 1);
                shareNum = Integer.parseInt(shareId.substring(1));
            } catch (NumberFormatException e) {
                return;
            }
            if (!(("n".equals(shareType) || "b".equals(shareType)) && shareNum > 0)) {
                return;
            }
        } else {
            return;
        }
        //业务处理
        if ("n".equals(shareType)) {
            //邀请业务
            Member member = loginMapper.selectByPrimaryKey(shareNum);
            //添加好友
            if(!fromUserId.equals(member.getId()) && friendMapper.selectNumFriend(fromUserId, member.getId()) == 0){
                FriendRel friendRel = new FriendRel();
                friendRel.setFriendOut(fromUserId);
                friendRel.setFriendIn(member.getId());
                friendMapper.insertSelective(friendRel);
            }
            if (member != null) {
                if (memberReturn.getMember() == null) {
                    //新用户
                    member.setNumWater(member.getNumWater() + 30000);
                    loginMapper.updateByPrimaryKeySelective(member);
                } else if (!member.getId().equals(memberReturn.getMember().getId())) {
                    if (logDataMapper.getShareNearlyNum(member.getId(), getToday()) < 3) {
                        if (logDataMapper.getShareNum(member.getId(), memberReturn.getMember().getId(), getToday()) == 0) {
                            if (logDataMapper.getHelpNearlyNum(memberReturn.getMember().getId(), getToday()) < 3) {
                                //被助加水
                                member.setNumWater(member.getNumWater() + 1500);
                                loginMapper.updateByPrimaryKeySelective(member);
                                //主动加水
                                memberReturn.getMember().setNumWater(memberReturn.getMember().getNumWater() + 1000);
                                //添加互助提醒
                                LogData logData = new LogData(member.getId(), "share", memberReturn.getMember().getId(), new Date());
                                logDataMapper.insertSelective(logData);
                                //获得水提醒
                                memberReturn.setShareAdd(1000);
                                memberReturn.setShareReturn(1);
                            } else {
                                memberReturn.setShareReturn(-1);  //帮助次数上限
                            }
                        } else {
                            memberReturn.setShareReturn(-3);  //帮助过他
                        }
                    } else {
                        memberReturn.setShareReturn(-2);  //被助上限
                    }
                }
            }
        } else {
            //砍价业务
            memberReturn.setShareReturn(2);
            Bargain bargain = bargainMapper.selectByPrimaryKey(shareNum);
            if (bargain != null) {
                //不管砍价过没过期，好友得加上
                if(!fromUserId.equals(bargain.getUserId()) && friendMapper.selectNumFriend(fromUserId,bargain.getUserId()) == 0){
                    FriendRel friendRel = new FriendRel();
                    friendRel.setFriendOut(fromUserId);
                    friendRel.setFriendIn(bargain.getUserId());
                    friendMapper.insertSelective(friendRel);
                }
                //砍价判断
                if (System.currentTimeMillis() - bargain.getBargainTime().getTime() < 86400000) {
                    if (bargain.getItemNow() < bargain.getItemPrice()) {
                        double downPrice = 0.01;
                        if (memberReturn.getMember() != null) {
                            if (bargainDataMapper.selectTodayNum(memberReturn.getMember().getId(), getToday()) != 0) {
                                memberReturn.setBargainFail(-1);
                                return;
                            }
                        } else {
                            downPrice = 0.1;
                        }

                        //修改价格
                        bargain.setItemNow(bargain.getItemNow() + downPrice);
                        bargainMapper.updateByPrimaryKeySelective(bargain);
                        //添加砍价记录
                        BargainData bargainData = new BargainData();
                        bargainData.setUserId(memberReturn.getMember().getId());
                        bargainData.setBargainDay(new Date());
                        bargainData.setBargainId(shareNum);
                        bargainData.setDownPrice(downPrice);
                        bargainDataMapper.insertSelective(bargainData);
                        memberReturn.setDownPrice(downPrice);
                        memberReturn.setBargainFail(1);

                    } else {
                        memberReturn.setBargainFail(-1);  //今天帮过别人
                    }
                } else {
                    memberReturn.setBargainFail(-2);  //砍价已完成
                }
            } else {
                memberReturn.setBargainFail(-3);  //砍价过期
            }
        }
    }

    /**
     * 获取用户待收货卷纸数量
     */
    private void getPaperWait(MemberReturn memberReturn) {
        //如果用户不存在直接返回
        if (memberReturn.getMember() == null) {
            return;
        }
        Integer wait = buyMapper.getOrderWait(memberReturn.getMember().getId());
        if(wait != null){
            memberReturn.setPaperWait(wait);
        }
    }

    /**
     * 获取用户砍价信息
     */
    private void getBargain(MemberReturn memberReturn) {
        //如果用户不存在直接返回
        if (memberReturn.getMember() == null) {
            return;
        }
        Bargain bargain = bargainMapper.getUserBargain(memberReturn.getMember().getId());
        if (bargain != null) {
            memberReturn.setBargainId(bargain.getId());
            memberReturn.setBargain(1);
        }
    }

    /**
     * 获取用户是否有没填写地址的发收件信息
     */
    private void getExpress(MemberReturn memberReturn) {
        //如果用户不存在直接返回
        if (memberReturn.getMember() == null) {
            return;
        }
        //用户存在
        Express express = expressMapper.selectNullExpress(memberReturn.getMember().getId());
        if (express != null) {
            memberReturn.setExpress(1);
            memberReturn.setExpressKey(express.getExpressKey());
            if (express.getItemType() == 1) {
                memberReturn.setExpressName("手纸12卷");
            } else if (express.getItemType() == 2) {
                memberReturn.setExpressName("手纸36卷");
            }
        }
    }

    /**
     * 获取用户当日各种活动签到使用情况
     *
     * @param memberReturn 需要返回的用户信息对象
     */
    private void getLogData(MemberReturn memberReturn) {
        //初始化
        memberReturn.setFortuneNum(5);
        //如果用户不存在直接返回
        if (memberReturn.getMember() == null) {
            return;
        }
        //用户存在
        List<LogData> logSql = logDataMapper.selectToday(memberReturn.getMember().getId(), getToday());
        for (LogData data : logSql) {
            if ("guess".equals(data.getLogType())) {
                memberReturn.setGuess(1);
            } else if ("fortune".equals(data.getLogType())) {
                memberReturn.setFortune(1);
                memberReturn.setFortuneNum(memberReturn.getFortuneNum() - 1);
            } else if ("vegetable".equals(data.getLogType())) {
                memberReturn.setVegetable(1);
            } else if ("meal1".equals(data.getLogType())) {
                memberReturn.setMeal1(1);
            } else if ("meal2".equals(data.getLogType())) {
                memberReturn.setMeal2(1);
            } else if ("meal3".equals(data.getLogType())) {
                memberReturn.setMeal3(1);
            } else if ("card".equals(data.getLogType())) {
                memberReturn.setCard(1);
            } else if ("store".equals(data.getLogType())) {
                memberReturn.setStore(1);
            } else if ("share".equals(data.getLogType())) {
                memberReturn.setShare(memberReturn.getShare() + 1);
            } else if ("invite".equals(data.getLogType())) {
                memberReturn.setInvite(memberReturn.getInvite() + 1);
            } else if ("login".equals(data.getLogType())) {
                memberReturn.setLogin(1);
            }
        }
        if (memberReturn.getLogin() == 0) {
            //查询用户本周已经签到多少次
            int week = getWeek() - 1;
            memberReturn.setLoginDay(logDataMapper.getLogDataNearlyNum(memberReturn.getMember().getId(), week));
        }
    }

    /**
     * 获取当前用户的好友列表
     *
     * @param memberReturn 需要返回的用户信息对象
     */
    private void getFriendList(MemberReturn memberReturn) {
        memberReturn.setFriendList(null);
        //用户存在，需要查表
        if (memberReturn.getMember() != null) {
            memberReturn.setFriendList(loginMapper.selectFriendList(memberReturn.getMember().getId()));
        }
    }

    /**
     * 获取用户是否已经有发短信记录
     *
     * @param memberReturn 用户返回信息组
     */
    private void getSendData(MemberReturn memberReturn) {
        memberReturn.setSendHidden(0);
        memberReturn.setSendTime(0);
        memberReturn.setMaybePhone("");
        //用户存在，需要查表
        if (memberReturn.getMember() != null) {
            SendData sendData = loginMapper.selectLastOneByUserId(memberReturn.getMember().getId(), getToday());
            if (sendData != null) {
                //获取多少秒之后可以再次发送
                int sendTime = (int) (System.currentTimeMillis() - ((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(sendData.getSendTime(),
                        new ParsePosition(0)).getTime() / 1000));
                memberReturn.setSendHidden(1);
                memberReturn.setSendTime(sendTime);
                memberReturn.setMaybePhone(sendData.getUserPhone());
            }
        }
    }

    /**
     * 抽取的一个获取当前日期的方法
     *
     * @return 返回yyyy-MM-dd 格式日期
     */
    private String getToday() {
        if (today == null || !(today.length() > 1)) {
            Date now = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            today = dateFormat.format(now);
        }
        return today;
    }

    /**
     * 获取当前是星期几
     *
     * @return 返回星期几，1-7
     */
    private int getWeek() {
        Calendar calendar;
        calendar = Calendar.getInstance();
        System.out.println(calendar);
        int week = calendar.get(calendar.DAY_OF_WEEK) - 1;
        if (week == 0) {
            week = 7;
        }
        return week;
    }
}
