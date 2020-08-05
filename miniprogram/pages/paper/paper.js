// pages/paper/paper.js
const app = getApp()
// 在页面中定义激励视频广告
let videoAd = null
// 在页面中定义插屏广告
let interstitialAd = null

Page({
  data: {
    //imgUrl: 'http://localhost:85/img/',
    imgUrl: 'https://qiadian.cn/0',
    avatarUrl: 'https://www.qiadian.cn/paper/avatar/',
    shareImgUrl: 'https://www.qiadian.cn/paper/shareImg/',
    apiUrl: app.globalData.apiUrl,
    pddUrl: "https://www.qiadian.cn/0pdd/api/pddapi.php?key=FKjv9MiAWnktVhrF&",

    //拼多多广告
    goAppHidden: true,
    adItemHidden: true,
    adPinHidden: true,
    adItemImg: "",
    adPinImg: "",
    adItemGo: [],
    adPinGo: [],

    //系统配置
    sendShow: true,
    paperTips: "",
    radioText: "",
    lineText1: "",
    lineText2: "",
    pushText: "",
    noticeHidden: true,
    noticeText: "",

    //分享信息
    shareId: 0,
    options: [],
    shareUrl: "",

    //用户信息
    userId: "",
    wxId: "",
    numMoney: "0",
    numPercentage: "",
    numWater: "",
    friendList: [],
    numPaper: 0,
    numExpress: 0,
    moneyCode: "0",
    allMoney: 0,
    eCode: "",
    nCode: "",

    //进度条
    rateCss: 0,

    //收获提醒
    radioWxId: 1,
    radioName: "**",
    radioTime: '25秒',
    radioHidden: true,

    //提醒收藏
    pageAddHidden: false,

    //手纸数量
    paperNum1: true,
    paperNum2: true,
    paperNum3: true,
    paperNum4: true,
    paperNum5: true,
    paperNum6: true,
    paperNum7: true,
    paperNum8: true,
    paperNum9: true,
    paperNum10: true,
    paperNum11: true,
    paperNum12: true,

    //说话
    sayHidden: true,
    sayText: "",

    //做任务窗口
    daySignLeft: 23,
    getAnimation: "",
    getHidden: true,
    daySignHidden: true,

    task1Title: "",
    task1Tips: "",
    task2Title: "",
    task2Tips: "",
    task3Title: "",
    task3Tips1: "",
    task3Tips2: "",
    task4Title: "",
    task4Tips: "",
    task5Title: "",
    task5Tips: "",
    task6Title: "",
    task6Tips: "",
    task7Title: "",
    task7Tips: "",
    task8Title: "",
    task8Tips: "",
    task9Tips: "",
    task9Title: "",
    task11Tips: "",
    task11Title: "",
    task12Tips: "",
    task12Title: "",

    getShareTime: 0,
    getMealTime: 0,
    getInviteTime: 0,
    getMammonTime: 0,
    getVegetableTime: 0,
    getGuessTime: 0,
    getFortuneTime: 0,
    getPhoneHidden: true,
    getShareHidden: true,
    getMealHidden: true,
    getInviteHidden: true,
    getMammonHidden: true,
    getVegetableHidden: true,
    getGuessHidden: true,
    getFortuneHidden: true,
    getShareNoHidden: true,
    getMealNoHidden: true,
    getInviteNoHidden: true,
    getMammonNoHidden: true,
    getVegetableNoHidden: true,
    getGuessNoHidden: true,
    getFortuneNoHidden: true,
    getCardHidden: true,
    getCardNoHidden: false,
    getStoreHidden: true,
    getStoreNoHidden: false,

    getMeal1: false,
    getMeal2: false,
    getMeal3: false,
    getMaelText: "",

    //水波
    circleHidden: true,
    waterPaperClick: "waterPaper",

    //分享窗口
    shareHidden: true,
    shareTips: "",

    //绑定手机
    phoneHidden: true,
    sendHidden: true,
    sendTime: 0,
    sendButton: "60秒后重新发送",
    sendCss: "background-color:#CCC;color:#333;",
    phoneNum: "",
    phoneCode: "0",
    testCodeClick: "testCode",

    //代言窗口
    transmitHidden: true,
    codeShow: "height:0rpx;",
    vipText: "非代言人",
    vipNum: 0,
    vipNoneHidden: true,
    vipTips: "",

    //猜红包
    guessHidden: true,

    //财神
    mammonHidden: true,
    mammonImg: "",
    mammonName: "",
    mammonText: "",
    mammonButton: "",
    mammonTips: "",

    //登录
    loginHidden: true,
    loginLoading: false,

    //收益
    moneyHidden: true,
    endorsementHidden: true,

    //收款码
    upCodeHidden: true,

    //兑换
    exchangeHidden: true,
    volumeCss: "border-bottom: 4rpx solid #F86C2C;",
    dipperCss: "border-bottom: 1rpx solid #FFBB59;",
    volumeHidden: false,
    dipperHidden: true,

    //规则
    ruleHidden: true,
    gradeCss: "border-bottom: 4rpx solid #F86C2C;",
    detailedCss: "border-bottom: 1rpx solid #FFBB59;",
    gradeHidden: false,
    detailedHidden: true,
    rule1: "",
    rule2: "",

    //获得
    rewardHidden: true,
    rewardNum: 0,

    //砍价
    helpHidden: true,

    //发货
    finishShow: true,
    expressCity: ['北京市', '北京市', '东城区'],
    expressCityName: "",
    expressUserName: "",
    expressUserAddress: "",
    expressUserPhone: "",
    expressKey: "",
    expressName: "",

    //新用户
    getNewUser: true,
    newUserNum: 0,
    newWaterNum: 0,
    newRateCss: 0,
    newPercentage: 0,

    //广告视频
    videoId: 0,

    //优惠券
    buyHidden: true,
    buyList: [],

    //待收货
    paperWaitHidden: true,
    paperWaitNum: 0
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log(options);
    var that = this;
    var url = that.data.apiUrl;
    if (options) {
      if (options.k) {
        that.setData({
          shareId: options.k,
        })
      }
      if (options.scene) {
        const scene = decodeURIComponent(options.scene);
        that.setData({
          shareId: options.scene,
        })
      }
    }

    console.log(that.data.shareId);
    //SH模式
    wx.request({
      url: 'https://www.qiadian.cn/ip/ip.php?key=n7qKancNi0uHjpq88&api=ip',
      success(net) {
        console.log(net)
        //net.data.dangerous = true
        if (net.data.dangerous == true) {
          app.globalData.sendShow = true;
          that.setData({
            sendShow: true
          })
        } else {
          if (net.data.dangerous == false) {
            app.globalData.sendShow = false;
            that.setData({
              sendShow: false
            })
          }
        }
      },
      fail(res) {
        that.loginShow();
      }
    })
    //判断登录缓存并联网效验
    try {
      var wxId = wx.getStorageSync('wxId');
      wx.showLoading({
        title: '努力加载中..',
      })
      if (wxId) {
        wx.request({
          url: url + 'api=getUser&wxId=' + wxId + '&shareId=' + that.data.shareId,
          success(loginCode) {
            console.log(loginCode);
            if (loginCode.data.fail == 1) { //登陆成功
              that.setUserShow(loginCode.data);
            } else {
              that.loginShow();
            }
          },
          fail(res) {
            that.loginShow();
          }
        })
      } else {
        //首次使用或清理了缓存，效验网络弹出登陆
        that.loginShow();
      }
    } catch (e) {
      that.loginShow();
    }

    // 在页面onLoad回调事件中创建激励视频广告实例
    if (wx.createRewardedVideoAd) {
      videoAd = wx.createRewardedVideoAd({
        adUnitId: 'adunit-b2c3caa7edf1f87f'
      })
      videoAd.onLoad(() => {})
      videoAd.onError((err) => {
        that.tips("啊哦，小影院打烊了~主人稍后再来吧~")
      })
      videoAd.onClose((res) => {
        if (res && res.isEnded || res === undefined) {
          that.tips("奖励已发放，请留意纸浆剩余~~")
          //领纸浆模式
          wx.request({
            url: url + 'api=videoGet&wxId=' + that.data.wxId,
            success(res) {
              that.setData({
                numWater: res.data.numWater
              })
            }
          })
        } else {
          that.tips("主人没有看完，不能给你奖励好难过~")
        }
      })
    }
    // 在页面onLoad回调事件中创建插屏广告实例
    if (wx.createInterstitialAd) {
      interstitialAd = wx.createInterstitialAd({
        adUnitId: 'adunit-e5909bfce9ca64a4'
      })
      interstitialAd.onLoad(() => {})
      interstitialAd.onError((err) => {})
      interstitialAd.onClose(() => {})
    }
  },

  /*用户登录 */
  getUserInfo: function (res) {
    var that = this;
    var url = that.data.apiUrl;
    var userInfo = res;
    console.log(res);
    that.setData({
      loginHidden: true
    });
    if (res.detail.errMsg == "getUserInfo:fail auth deny") {
      that.setData({
        loginLoadingShow: false
      });
      wx.showModal({
        title: '授权提示',
        content: '天天领卷纸需要您最基本信息授权（头像和昵称），请您点击允许方可继续使用本小程序。',
      })
    } else {
      wx.login({
        success: function (log) {
          console.log(log.code);
          wx.request({
            url: url + 'api=loginUser&code=' + log.code + '&userAvatar=' + userInfo.detail.userInfo.avatarUrl + '&userName=' + userInfo.detail.userInfo.nickName + '&userSex=' + userInfo.detail.userInfo.gender + '&shareId=' + that.data.shareId,
            success: function (loginCode) {
              console.log("用户首次登陆信息获取：")
              console.log(loginCode);
              that.setUserShow(loginCode.data);
            }
          })
        },
        fail: function (log) {
          console.log(log);
        },
      })
    }
  },


  /**数据通信层 */
  sendCode: function () { //绑定手机
    var that = this;
    console.log("倒计时：" + that.data.sendTime);
    if (that.data.sendTime == 0) {
      var url = that.data.apiUrl;
      var userPhone = that.data.phoneNum;
      var isNum = /^\d+$/.test(userPhone);
      if (userPhone.length == 11 && isNum == true) {
        that.phoneNext();
        that.sendTimeDown(60);
        wx.request({
          url: url + "api=phoneSend&userPhone=" + userPhone + "&wxId=" + that.data.wxId,
          success: function (res) {
            console.log(res);
            if (res.data == 1) {

            } else {
              that.setData({
                sendHidden: true,
              })
              if (res.data == -7) {
                that.tips("手机号格式错误，检查一下吧~");
              }
              if (res.data == -1) {
                that.tips("尝试次数过多，请明天再试~");
              }
              if (res.data == -2) {
                that.tips("你的账号已经绑定过手机了~");
              }
              if (res.data == -3) {
                that.tips("手机号已被其他账号占用~");
              }
              if (res.data == -4) {
                that.tips("同IP发信过多，明天再试吧~");
              }
              if (res.data == -5) {
                that.tips("本号码请求过多，自动开启保护模式，请绑定其他账号~");
              }
              if (res.data == -6) {
                that.tips("发信平台似乎有点问题，过会再试吧~");
              }
            }
          }
        })
      } else {
        that.tips("手机号格式错误，检查一下吧~");
      }
    } else {
      that.tips("再等一下，短信马上来了~");
    }
  },

  testCode: function () {
    var that = this;
    var url = that.data.apiUrl;
    var phoneCode = that.data.phoneCode;
    var isNum = /^\d+$/.test(phoneCode);
    if (phoneCode.length == 6 && isNum == true) {
      that.setData({
        testCodeClick: ''
      })
      wx.request({
        url: url + 'api=testCode&code=' + phoneCode + '&wxId=' + that.data.wxId,
        success: function (res) {
          console.log(res);
          if (res.data.fail == 1) {
            that.setData({
              numWater: res.data.numWater,
              sendHidden: true,
              getPhoneHidden: true,
            })
            that.getTips(res.data.addWater);
          } else {
            that.setData({
              testCodeClick: 'testCode'
            })
          }
          if (res.data.fail == -1) {
            that.tips('验证码格式错误');
          }
          if (res.data.fail == -2) {
            that.tips('验证码错误');
          }
        }
      })
    } else {
      that.tips('验证码格式错误~');
    }
  },

  getGuess: function () {
    var that = this;
    var url = that.data.apiUrl;
    wx.request({
      url: url + 'api=getGuess&wxId=' + that.data.wxId,
      success: function (res) {
        console.log(res);
        if (res.data.fail == 1) {
          that.setData({
            numWater: res.data.numWater,
          })
          that.getTips(res.data.addWater);
          that.getGuessDo();
        }
        if (res.data.fail == -1) {
          that.getGuessDo();
        }
      }
    })
  },

  getVegetable: function () {
    var that = this;
    var url = that.data.apiUrl;
    wx.request({
      url: url + 'api=getVegetable&wxId=' + that.data.wxId,
      success: function (res) {
        console.log(res);
        if (res.data.fail == 1) {
          that.setData({
            numWater: res.data.numWater,
          })
          that.getTips(res.data.addWater);
          that.getVegetableDo();
        }
        if (res.data.fail == -1) {
          that.tips("今日奖励已领~");
          that.getVegetableDo();
        }
      }
    })
  },

  getMeal: function () {
    var that = this;
    var url = that.data.apiUrl;
    wx.request({
      url: url + 'api=getMeal&wxId=' + that.data.wxId,
      success: function (res) {
        console.log(res);
        that.getMealDo();
        if (res.data.fail == 1) {
          that.setData({
            numWater: res.data.numWater,
          })
          that.getTips(res.data.addWater);
        }
        if (res.data.fail == -2) {
          that.tips("现在不是打卡时间哟~");
        }
        if (res.data.fail == -1) {
          that.tips("这顿都打卡过啦~");
        }
      }
    })
  },

  getSign: function () {
    var that = this;
    var url = that.data.apiUrl;
    wx.request({
      url: url + 'api=getSign&wxId=' + that.data.wxId,
      success: function (res) {
        console.log(res);
        if (res.data.fail == 1) {
          that.setData({
            numWater: res.data.numWater,
            daySignHidden: true
          })
          that.getTips(res.data.addWater);
        }
        if (res.data.fail == -1) {
          that.tips("今天已经领过啦~");
          that.setData({
            daySignHidden: true
          })
        }
      }
    })
  },

  expressSend: function () {
    var that = this;
    var url = that.data.apiUrl;
    var userAddress = that.data.expressCity[0] + that.data.expressCity[1] + that.data.expressCity[2] + that.data.expressUserAddress;
    wx.request({
      url: url + 'api=expressSend&expressKey=' + that.data.expressKey + '&userName=' + that.data.expressUserName + '&userPhone=' + that.data.expressUserPhone + '&userAddress=' + userAddress,
      success(data) {
        console.log(data);
        if (data.data == 1) {
          that.tips("发货提交成功！");
          that.setData({
            finishShow: true
          })
        }
      }
    })
  },

  waterPaper: function () {
    var that = this;
    var url = that.data.apiUrl;
    that.circleShow();
    // 在适合的场景显示插屏广告
    if (interstitialAd) {
      interstitialAd.show().catch((err) => {
        console.error(err)
      })
    }
    if (that.data.numWater >= 600) {
      that.setData({
        waterPaperClick: "",
      })
      that.paperSay("谢谢你给我浇水，我感觉自己长得更快了");
      wx.request({
        url: url + 'api=waterPaper&wxId=' + that.data.wxId,
        success(res) {
          console.log(res);
          if (res.data.fail == 1) {
            that.shortTips("-600木浆");
            var numPercentage = 100 - (res.data.numPercentage % 100);
            numPercentage = numPercentage.toFixed(2);
            that.setData({
              numPercentage: numPercentage,
              numWater: res.data.numWater,
            })
            that.setPaperNum(parseInt(res.data.numPercentage / 100));
            that.rateShow();
            if (res.data.expressKey != 0) {
              that.setData({
                finishShow: false,
                expressKey: res.data.expressKey,
                expressName: "卷纸一提12卷"
              })
            }
          }
          if (res.data.fail == -1) {
            that.tips("啊哦，木浆不够了，快去领取一些吧~");
            that.getInShow();
          }
          that.setData({
            waterPaperClick: "waterPaper",
          })
        }
      })
    } else {
      that.tips("啊哦，木浆不够了，快去领取一些吧~");
      that.getInShow();
    }
  },

  //去拼多多拼单
  goBuy: function (res) {
    var goods = res.currentTarget.dataset.id;
    var url = app.globalData.pddapi;
    wx.request({
      url: url + 'api=pdd.ddk.goods.other&custom_parameters=' + wx.getStorageSync('wxId') + '&p_id=1013606_128905068&goods_id_list=' + goods,
      success(res) {
        console.log(res);
        wx.navigateToMiniProgram({
          appId: 'wx32540bd863b27570',
          path: res.data.goods_promotion_url_generate_response.goods_promotion_url_list[0].we_app_info.page_path,
        })
      }
    })
  },

  moneyOut: function () {
    var that = this;
    var url = that.data.apiUrl;
    if (that.data.numMoney >= 5) {
      if (that.data.moneyCode != 0 && that.data.moneyCode != '') {
        wx.request({
          url: url + 'api=moneyOut&wxId=' + that.data.wxId,
          success: function (res) {
            console.log(res);
            if (res.data.fail == 1) {
              that.setData({
                numMoney: 0,
                moneyHidden: true,
                allMoney: that.data.allMoney + numMoney
              })
              that.tips("提现成功！");
            }
            if (res.data.fail == -1) {
              that.tips("需满5元才能提现哟~");
            }
          }
        })
      } else {
        that.tips("您还没绑定收款账号，请先上传二维码！");
        that.setData({
          upCodeHidden: false,
          moneyHidden: true
        })
      }
    } else {
      that.tips("需满5元才能提现哟~")
    }
  },

  upCode: function (code) {
    var that = this;
    var url = that.data.apiUrl;
    wx.request({
      url: url + 'api=upCode&wxId=' + that.data.wxId + '&moneyCode=' + code,
      success: function (res) {
        console.log(res);
        if (res.data == 1) {
          that.tips("上传成功！现在可以申请提现了");
          that.setData({
            upCodeHidden: true,
            moneyHidden: false,
            moneyCode: code,
          })
        } else {
          that.tips("上传失败请稍后再试！");
        }
      }
    })
  },

  getCodeE: function (userId) {
    var that = this;
    var url = that.data.apiUrl;
    wx.request({
      url: url + 'api=getCodeE&id=' + userId,
      success: function (res) {
        console.log(res.data);
        var imgData = res.data;
        imgData = imgData.replace(/[\r\n]/g, '');
        that.setData({
          eCode: imgData
        })
        wx.setStorageSync('eCode', imgData);
      }
    })
  },

  getCodeN: function (userId) {
    var that = this;
    var url = that.data.apiUrl;
    wx.request({
      url: url + 'api=getCodeN&id=' + userId,
      success: function (res) {
        console.log(res.data);
        var imgData = res.data;
        imgData = imgData.replace(/[\r\n]/g, '');
        that.setData({
          nCode: imgData
        })
        wx.setStorageSync('nCode', imgData);
      }
    })
  },

  /**订阅消息*/
  getMessage: function () {
    var that = this;
    var url = that.data.apiUrl;
    var wxId = that.data.wxId;
    wx.getSystemInfo({
      success(res) {
        var version = res.SDKVersion;
        version = version.replace(/\./g, "");
        console.log(version);
        if (parseInt(version) < 230) {} else {
          wx.requestSubscribeMessage({
            tmplIds: ["qPMBmuAiQkmkTbX6f7op_TZIoKmuVMuYmsRCYIIpizQ", "e9uSKNKkukIVAHsCbpy9fUcYVw1mNgZiGJeRzSQTHgM", "XRfBEtU_3G_YNTFAmFJ5EwjLjNSmqnNEXGnV1c3zYE8"],
            success: function (res) {
              if (res.qPMBmuAiQkmkTbX6f7op_TZIoKmuVMuYmsRCYIIpizQ == 'accept' || res.e9uSKNKkukIVAHsCbpy9fUcYVw1mNgZiGJeRzSQTHgM == 'accept' || res.XRfBEtU_3G_YNTFAmFJ5EwjLjNSmqnNEXGnV1c3zYE8 == 'accept') {
                var fahuo = 0;
                var huodong = 0;
                var daka = 0;
                if (res.qPMBmuAiQkmkTbX6f7op_TZIoKmuVMuYmsRCYIIpizQ == 'accept') {
                  fahuo = 1;
                }
                if (res.e9uSKNKkukIVAHsCbpy9fUcYVw1mNgZiGJeRzSQTHgM == 'accept') {
                  huodong = 1;
                }
                if (res.XRfBEtU_3G_YNTFAmFJ5EwjLjNSmqnNEXGnV1c3zYE8 == 'accept') {
                  daka = 1;
                }
                wx.request({
                  url: url + 'api=cardDo&wxId=' + wxId + '&fahuo=' + fahuo + '&huodong=' + huodong + '&daka=' + daka,
                  success: function (res) {
                    console.log(res);
                    if (res.data.fail == 1) {
                      that.setData({
                        numWater: res.data.numWater,
                        getCardHidden: true,
                        getCardNoHidden: false
                      })
                      that.getTips(res.data.addWater);
                    }
                    if (res.data.fail == -1) {
                      that.tips("今天已经领过啦~");
                      that.setData({
                        getCardHidden: true,
                        getCardNoHidden: false
                      })
                    }
                  }
                })
              } else {
                that.tips("您需要同意订阅才可以哟~~")
              }
            },
          })
        }
      }
    })
  },

  /**展示神券广告 */
  getBuyItem: function () {
    var that = this;
    var url = app.globalData.pddapi;
    var page = Math.ceil(Math.random()*10) % 3 + 1; 
    wx.request({
      url: url + 'api=pdd.ddk.goods.search.nomal&page=' + page,
      success(res) {
        console.log(res)
        var returnList = res.data.goods_search_response.goods_list;
        that.setData({
          buyList: that.setReturn(returnList),
          buyHidden: false
        })
      }
    })
  },

  //批量处理
  setReturn: function (returnList) {
    var that = this;
    var i = 0;
    for (i = 0; i < returnList.length; i++) {
      returnList[i] = that.listOnceDo(returnList[i])
      returnList[i].showLow = that.data.lowHidden
    }
    return returnList;
  },
  
  //数组内部处理
  listOnceDo: function (returnList) {
    if(app.globalData.special == true){
      returnList.special = true
    }else{
      returnList.special = false
    }
    if (returnList.has_coupon == true) {
      returnList.has_coupon_hidden = false;
      if (returnList.min_group_price >= returnList.coupon_min_order_amount) {
        returnList.min_group_price = returnList.min_group_price - returnList.coupon_discount;
      }
    } else {
      returnList.has_coupon_hidden = true;
      returnList.coupon_discount = 0;
    }

    returnList.min_group_price = returnList.min_group_price * 0.01;
    returnList.promotion_num = returnList.min_group_price * returnList.promotion_rate * 0.001;
    returnList.min_group_price = returnList.min_group_price.toFixed(2);
    returnList.hidden = true
    returnList.paperNum = 1
    if(returnList.promotion_num > 2.4){
      returnList.hidden = false
      returnList.paperNum = 2
    }
    returnList.coupon_discount = returnList.coupon_discount*0.01;
    return returnList;
  },

  /**前端绘制层 */
  openVideoAd: function () {
    console.log('打开激励视频');
    // 在合适的位置打开广告
    if (videoAd) {
      videoAd.show().catch(err => {
        // 失败重试
        videoAd.load()
          .then(() => videoAd.show())
      })
    }
  },
  setUserShow: function (login) { //前端数据绘制
    var that = this;
    wx.hideLoading();
    //拉取广告
    var day2 = new Date();
    day2.setTime(day2.getTime());
    var s = day2.getFullYear() + "-" + (day2.getMonth() + 1) + "-" + day2.getDate();
    if(wx.getStorageSync('itemShow')){
      if(wx.getStorageSync('itemShow') != s){
        that.getBuyItem();
        wx.setStorageSync('itemShow', s);
      }
    }else{
      that.getBuyItem();
      wx.setStorageSync('itemShow', s);
    }
    //参数设置
    var numPercentage = 100 - (login.numPercentage % 100);
    numPercentage = numPercentage.toFixed(2);
    
    that.setData({
      loginShowSHHidden: true,
      userId: login.userId,
      wxId: login.wxId,
      numMoney: login.numMoney,
      numPercentage: numPercentage,
      numWater: login.numWater,
      newWaterNum: login.numWater,
      friendList: login.friendList,
      shareUrl: "?k=n" + login.userId,
      vipNum: login.vipNum,
      numPaper: login.numPaper,
      numExpress: login.numExpress,
      moneyCode: login.moneyCode,
      allMoney: login.allMoney,
      vipTips: login.vipTips,
      adItemImg: login.adItemImg,
      adPinImg: login.adPinImg,

      lineText1: login.set[0].lineText1,
      lineText2: login.set[0].lineText2,
      paperTips: login.set[0].paperTips,
      pushText: login.set[0].pushText,
      pushTips: login.set[0].pushTips,
      radioText: login.set[0].radioText,
      rule1: login.set[0].rule1.replace(/enter/g, " \n"),
      rule2: login.set[0].rule2.replace(/enter/g, " \n"),
      shareTips: login.set[0].shareTips,
      task1Tips: login.set[0].task1Tips,
      task1Title: login.set[0].task1Title,
      task2Tips: login.set[0].task2Tips,
      task2Title: login.set[0].task2Title,
      task3Tips1: login.set[0].task3Tips1,
      task3Tips2: login.set[0].task3Tips2,
      task3Title: login.set[0].task3Title,
      task4Tips: login.set[0].task4Tips,
      task4Title: login.set[0].task4Title,
      task5Tips: login.set[0].task5Tips,
      task5Title: login.set[0].task5Title,
      task6Tips: login.set[0].task6Tips,
      task6Title: login.set[0].task6Title,
      task7Tips: login.set[0].task7Tips,
      task7Title: login.set[0].task7Title,
      task8Tips: login.set[0].task8Tips,
      task8Title: login.set[0].task8Title,
      task9Tips: login.set[0].task9Tips,
      task9Title: login.set[0].task9Title,
      task11Tips: login.set[0].task11Tips,
      task11Title: login.set[0].task11Title,
      task12Tips: login.set[0].task12Tips,
      task12Title: login.set[0].task12Title,
      mammonText: login.set[0].mammonText,
      mammonButton: login.set[0].mammonButton,
      mammonTips: login.set[0].mammonTips,
      noticeHidden: login.set[0].noticeHidden10,
      noticeText: login.set[0].noticeText10,
    })
    that.setPaperNum(parseInt(login.numPercentage / 100));
    that.rateShow();
    //新用户
    if (login.newUser == true) {
      that.setData({
        newUser: 0
      })
    }
    //代言人等级
    if (login.vip == 0) {
      that.setData({
        vipText: "非代言人",
        vipNoneHidden: false
      })
    }
    if (login.vip == 1) {
      that.setData({
        vipText: "黄金代言人",
        vipNoneHidden: true
      })
    }
    if (login.vip == 2) {
      that.setData({
        vipText: "铂金代言人",
        vipNoneHidden: true
      })
    }
    if (login.vip == 3) {
      that.setData({
        vipText: "钻石代言人",
        vipNoneHidden: true
      })
    }
    //展示等待收货
    if(login.paperWait != '0'){
      that.setData({
        paperWaitHidden: false,
        paperWaitNum: login.paperWait
      })
    }
    //太阳码
    var nCode = wx.getStorageSync('nCode');
    var eCode = wx.getStorageSync('eCode');
    if (nCode) {
      that.setData({
        nCode: nCode
      })
    } else {
      that.getCodeN(login.userId);
    }

    if (eCode) {
      that.setData({
        eCode: eCode
      })
    } else {
      if (login.vip != 0) {
        that.getCodeE(login.userId);
      }
    }
    //本地储存
    wx.setStorageSync('wxId', login.wxId);
    //分享工作
    if (login.shareReturn == 1) {
      that.tips("谢谢你帮助我助力~您也获得了" + login.shareAdd + "纸浆");
    }
    if (login.shareReturn == -1) {
      that.tips("助理次数已经用尽啦~");
    }
    if (login.shareReturn == -2) {
      that.tips("他已经被助力满啦，换个人试试吧~");
    }
    if (login.shareReturn == -3) {
      that.tips("你今天已经帮过他啦~");
    }
    //绑定手机
    if (login.userPhone == 0) {
      that.setData({
        getPhoneHidden: false
      })
    }
    if (login.userPhone == 0 && login.sendHidden == 1) {
      that.sendTimeDown(login.sendTime);
      that.setData({
        sendHidden: false,
        phoneNum: login.maybePhone,
        getPhoneHidden: true
      })
    }
    //填写地址
    if (login.express == 1) {
      that.setData({
        finishShow: false,
        expressKey: login.expressKey,
        expressName: login.expressName
      })
    }
    //三餐打卡
    if (login.meal1 == 0) {
      that.setData({
        getMeal1: false,
      })
    } else {
      that.setData({
        getMeal1: true,
      })
    }

    if (login.meal2 == 0) {
      that.setData({
        getMeal2: false,
      })
    } else {
      that.setData({
        getMeal2: true,
      })
    }

    if (login.meal3 == 0) {
      that.setData({
        getMeal3: false,
      })
    } else {
      that.setData({
        getMeal3: true,
      })
    }

    that.mealCountDown();
    //任务数量
    if (login.mammon == 1) {
      that.setData({
        getMammonHidden: false,
        getMammonNoHidden: true,
        mammonImg: that.data.avatarUrl + login.mammonWxId + '.jpg',
        mammonName: login.mammonName
      })
    }

    if (login.store == 0) {
      getApp().globalData.store = 0;
      that.setData({
        getStoreHidden: false,
        getStoreNoHidden: true,
      })
    } else {
      that.setData({
        getStoreHidden: true,
        getStoreNoHidden: false,
      })
      getApp().globalData.store = 1;
    }

    if (login.fortuneNum > 0) {
      that.setData({
        getFortuneHidden: false,
        getFortuneNoHidden: true,
      })
    } else {
      that.setData({
        getFortuneHidden: true,
        getFortuneNoHidden: false,
      })
    }
    getApp().globalData.fortuneNum = login.fortuneNum;

    if (login.card == 0) {
      that.setData({
        getCardHidden: false,
        getCardNoHidden: true,
      })
    } else {
      that.setData({
        getCardHidden: true,
        getCardNoHidden: false,
      })
    }

    if (login.guess == 0) {
      that.setData({
        getGuessHidden: false,
        getGuessNoHidden: true,
      })
    } else {
      that.setData({
        getGuessHidden: true,
        getGuessNoHidden: false,
      })
    }

    if (login.vegetable == 0) {
      that.setData({
        getVegetableHidden: false,
        getVegetableNoHidden: true,
      })
    } else {
      that.setData({
        getVegetableHidden: true,
        getVegetableNoHidden: false,
      })
    }

    if (login.invite < 3) {
      that.setData({
        getInviteHidden: false,
        getInviteNoHidden: true,
        getInviteTime: login.invite
      })
    } else {
      that.setData({
        getInviteHidden: true,
        getInviteNoHidden: false,
      })
    }

    if (login.share < 3) {
      that.setData({
        getShareHidden: false,
        getShareNoHidden: true,
        getShareTime: login.share
      })
    } else {
      that.setData({
        getShareHidden: true,
        getShareNoHidden: false,
      })
    }

    if (login.login == 0) {
      that.setData({
        daySignHidden: false
      })
      that.daySignSet(login.loginDay + 1);
    } else {
      that.setData({
        daySignHidden: true
      })
    }
    //邀请
    if (login.invite == 1) {
      that.tips("助力成功~");
    }

    //滚动信息
    that.radioShow();
    that.setData({
      radioHidden: false
    })
    that.paperSay("主人好，一会没见你都觉得好想你呀~");

    //地址获取
    wx.request({
      url: 'https://www.qiadian.cn/ip/ip.php?key=n7qKancNi0uHjpq88&api=ip',
      success(res) {
        console.log(res);
        if (res.data.dangerous == true && login.numPercentage < 250) {
          that.showLow()
        }else{
          that.setData({
            goAppHidden: false,
          })
        }
      }
    })
  },

  //应急避险
  showLow: function () {
    var that = this;
    that.setData({
      getPhoneHidden: true,
      getShareHidden: true,
      getCardHidden: true,
      getStoreHidden: true,
      getInviteHidden: true,
      getVegetableHidden: true,
      getShareNoHidden: true,
      getCardNoHidden: true,
      getStoreNoHidden: true,
      getInviteNoHidden: true,
      getVegetableNoHidden: true,
      adItemHidden: true,
      adPinHidden: true,
      endorsementHidden: true,
      goAppHidden: true,
    })
  },

  open888:function(){
    this.openVideoAd();
    this.rewardClose();
  },

  scanCode: function () { //识别二维码
    var that = this;
    wx.scanCode({ //扫描API
      success(res) { //扫描成功
        console.log(res) //输出回调信息
        if (res.result.search("wxp://") != -1) {
          that.upCode(res.result);
        } else {
          that.tips("您的不是收钱码，请检查~")
        }
      }
    })
  },
  downImg: function () {
    var that = this;
    that.tips("正在保存");
    var imgUrl = that.data.shareImgUrl + that.data.userId + '.png';
    console.log(imgUrl);
    wx.downloadFile({
      url: imgUrl,
      success: function (res) {
        if (res.statusCode === 200) {
          let img = res.tempFilePath;
          wx.saveImageToPhotosAlbum({
            filePath: img,
            success(res) {
              that.tips("已保存到手机相册");
            },
            fail(res) {
              that.tips("您拒绝了保存相册！");
            }
          });
        }
      }
    });
  },
  radioShow: function () { //滚动文字
    var that = this;
    var num = 1;
    var name = ['9*', '张**', 'A**', '孤**', '幸**', '平**', '年**', '九**', '霍**'];
    setInterval(function () {
      that.setData({
        radioWxId: num,
        radioName: name[num],
        radioTime: num * 7 + '秒',
      })
      num = num + 1;
      if (num > 8) {
        num = 1;
      }
    }, 5000)
  },
  mealCountDown: function () { //三餐打卡定时器、十二点刷新定时器
    var nowDate = new Date();
    var that = this;
    var date = nowDate.getDate();
    var logCountDown = setInterval(function () {
      var now = new Date();
      var day = now.getDate();
      var hour = now.getHours();
      if (hour < 6) { //未到早餐打卡
        that.setData({
          getMealHidden: true,
          getMealNoHidden: false,
          getMealText: "未到时间"
        })
      }
      if (hour >= 6 && hour < 9) { //早餐打卡期间
        if (that.data.getMeal1 == true) { //用户已经打卡
          that.setData({
            getMealHidden: true,
            getMealNoHidden: false,
            getMealText: "已打卡"
          })
        } else {
          that.setData({
            getMealHidden: false,
            getMealNoHidden: true,
            getMealTime: 0
          })
        }
      }
      if (hour >= 9 && hour < 12) {
        that.setData({
          getMealHidden: true,
          getMealNoHidden: false,
          getMealText: "未到时间"
        })
      }
      if (hour >= 12 && hour < 14) { //午餐打卡期间
        if (that.data.getMeal2 == true) { //用户已经打卡
          that.setData({
            getMealHidden: true,
            getMealNoHidden: false,
            getMealText: "已打卡"
          })
        } else {
          that.setData({
            getMealHidden: false,
            getMealNoHidden: true,
            getMealTime: 1
          })
        }
      }
      if (hour >= 14 && hour < 18) {
        that.setData({
          getMealHidden: true,
          getMealNoHidden: false,
          getMealText: "未到时间"
        })
      }
      if (hour >= 18 && hour < 20) { //晚餐打卡期间
        if (that.data.getMeal3 == true) { //用户已经打卡
          that.setData({
            getMealHidden: true,
            getMealNoHidden: false,
            getMealText: "已打卡"
          })
        } else {
          that.setData({
            getMealHidden: false,
            getMealNoHidden: true,
            getMealTime: 2
          })
        }
      }
      if (hour >= 20) {
        that.setData({
          getMealHidden: true,
          getMealNoHidden: false,
          getMealText: "未到时间"
        })
      }
      if (day != date) {
        nowDate = new Date();
        date = nowDate.getDate();
        that.setData({
          //全部变量初始化
        })
        that.onLoad(that.data.options);
      }
    }, 1000)
  },
  sendTimeDown: function (time) { //短信倒计时
    var that = this;
    if (time == 0) {
      that.setData({
        sendTime: 0,
        sendButton: "重新发送",
        sendCss: "background-color:#F86C2C;color:#FFF;",
      })
    } else {
      var sendTimeDown = setInterval(function () {
        time = time - 1;
        that.setData({
          sendTime: time,
          sendButton: time + "秒后可重新发送",
          sendCss: "background-color:#CCC;color:#333;",
        })
        if (time == 0) {
          clearInterval(sendTimeDown);
          that.setData({
            sendTime: 0,
            sendButton: "重新发送",
            sendCss: "background-color:#F86C2C;color:#FFF;",
          })
        };
      }, 1000);
    }
  },

  /**基础开关层 */
  bargainGo: function () {
    var that = this;
    wx.navigateTo({
      url: '/pages/bargain/bargain?id=' + that.data.bargainId + '&price=-1'
    })
  },
  getMealDo: function () {
    var that = this;
    var now = new Date();
    var hour = now.getHours();
    if (hour >= 6 || hour < 8) {
      that.setData({
        getMeal1: true,
        getMealHidden: true,
        getMealNoHidden: false,
        getMealText: "已打卡"
      })
    }
    if (hour >= 12 || hour < 14) {
      that.setData({
        getMeal2: true,
        getMealHidden: true,
        getMealNoHidden: false,
        getMealText: "已打卡"
      })
    }
    if (hour >= 18 || hour < 20) {
      that.setData({
        getMeal3: true,
        getMealHidden: true,
        getMealNoHidden: false,
        getMealText: "已打卡"
      })
    }
  },
  seach: function () {
    this.tips("啊哦，还没有相关内容，我们已经记录会尽快补充哟~")
  },
  upCodeClose: function () {
    this.setData({
      upCodeHidden: true
    })
  },
  getGuessDo: function () {
    this.setData({
      guessHidden: true,
      getGuessHidden: true,
      getGuessNoHidden: false
    })
  },
  getVegetableDo: function () {
    this.setData({
      getVegetableNoHidden: false,
      getVegetableHidden: true
    })
  },
  getStoreDo: function () {
    this.setData({
      getStoreNoHidden: false,
      getStoreHidden: true
    })
  },
  getTips: function (num) { //获得木浆提醒
    var that = this;
    that.paperSay("主人好棒，又获得了" + num + "木浆，好开心~");
    that.setData({
      rewardHidden: false,
      rewardNum: num
    })
  },
  rewardClose: function () {
    this.setData({
      rewardHidden: true
    })
  },
  getPhoneNum: function (e) { //手机号码输入框
    this.setData({
      phoneNum: e.detail.value
    })
  },
  getPhoneCode: function (e) { //验证码输入框
    this.setData({
      phoneCode: e.detail.value
    })
  },
  buyClose: function(){
    this.setData({
      buyHidden: true
    })
  },
  phoneNext: function () { //等待输入验证码
    this.setData({
      sendHidden: false,
      phoneHidden: true
    })
  },
  tips: function (text) { //全局提示
    wx.showToast({
      title: text,
      icon: 'none',
      duration: 2500
    })
  },
  shortTips: function (text) { //全局提示
    wx.showToast({
      title: text,
      icon: 'none',
      duration: 1000
    })
  },
  rateShow: function () {
    var rate = 100 - this.data.numPercentage;
    this.setData({
      rateCss: rate * 0.01 * 356
    })
  },
  loginShow: function () {
    var that = this;
    console.log("23333333")
    wx.hideLoading();
    wx.request({
      url: 'https://www.qiadian.cn/ip/ip.php?key=n7qKancNi0uHjpq88&api=ip',
      success(net) {
        console.log('登陆：' + net.data)
        //net.data.dangerous = true
        if (net.data.dangerous == true) {
          that.setData({
            pushText: "浇灌纸浆",
            pushTips: "此功能可能需要登陆",
            boxHidden: true,
            cookHidden: false,
            loginShowSHHidden: false
          })
        } else {
          if (net.data.dangerous == false) {
            that.setData({
              loginHidden: false
            })
          }
        }
      }
    })
  },
  loginShowSH:function(){
    var that = this;
    that.setData({
      loginHidden: false
    })
  },
  phoneClose: function () {
    this.setData({
      phoneHidden: true
    })
  },
  paperSay: function (text) { //卷纸说话
    var that = this;
    this.setData({
      sayHidden: false,
      sayText: text
    })
    setTimeout(function () {
      that.setData({
        sayHidden: true,
      })
    }, 5000);
  },
  daySignSet: function (day) { //每日签到按钮设定
    var that = this;
    if (day == 1) {
      that.setData({
        daySignLeft: 23
      })
    }
    if (day == 2) {
      that.setData({
        daySignLeft: 120
      })
    }
    if (day == 3) {
      that.setData({
        daySignLeft: 220
      })
    }
    if (day == 4) {
      that.setData({
        daySignLeft: 320
      })
    }
    if (day == 5) {
      that.setData({
        daySignLeft: 420
      })
    }
    if (day == 6) {
      that.setData({
        daySignLeft: 515
      })
    }
    if (day == 7) {
      that.setData({
        daySignLeft: 610
      })
    }
  },
  pageAddClose: function () { //添加提醒关闭
    this.setData({
      pageAddHidden: true
    })
  },
  setPaperNum: function (data) { ///设置卷纸数量
    var that = this;
    if (data > 0) {
      that.setData({
        paperNum1: false
      })
    } else {
      that.setData({
        paperNum1: true
      })
    }
    if (data > 1) {
      that.setData({
        paperNum2: false
      })
    } else {
      that.setData({
        paperNum2: true
      })
    }
    if (data > 2) {
      that.setData({
        paperNum3: false
      })
    } else {
      that.setData({
        paperNum3: true
      })
    }
    if (data > 3) {
      that.setData({
        paperNum4: false
      })
    } else {
      that.setData({
        paperNum4: true
      })
    }
    if (data > 4) {
      that.setData({
        paperNum5: false
      })
    } else {
      that.setData({
        paperNum5: true
      })
    }
    if (data > 5) {
      that.setData({
        paperNum6: false
      })
    } else {
      that.setData({
        paperNum6: true
      })
    }
    if (data > 6) {
      that.setData({
        paperNum7: false
      })
    } else {
      that.setData({
        paperNum7: true
      })
    }
    if (data > 7) {
      that.setData({
        paperNum8: false
      })
    } else {
      that.setData({
        paperNum8: true
      })
    }
    if (data > 8) {
      that.setData({
        paperNum9: false
      })
    } else {
      that.setData({
        paperNum9: true
      })
    }
    if (data > 9) {
      that.setData({
        paperNum10: false
      })
    } else {
      that.setData({
        paperNum10: true
      })
    }
    if (data > 10) {
      that.setData({
        paperNum11: false
      })
    } else {
      that.setData({
        paperNum11: true
      })
    }
    if (data > 11) {
      that.setData({
        paperNum12: false
      })
    } else {
      that.setData({
        paperNum12: true
      })
    }
  },
  goPlant: function () { //浇水种菜跳转
    var that = this;
    wx.navigateToMiniProgram({
      appId: 'wx2d8392025ca089a3',
      path: 'pages/tree/tree',
      success: function () {
        if (that.data.getVegetableHidden == false) {
          that.getVegetable();
          that.getVegetableDo();
        }
      },
    })
  },
  getInShow: function () { //做任务窗口打开
    this.setData({
      getAnimation: "getIn",
      getHidden: false,
    })
  },
  getOutShow: function () {
    var that = this;
    this.setData({
      getAnimation: "getOut"
    })
    setTimeout(function () {
      that.setData({
        getHidden: true,
      })
    }, 200);
  },
  shareClose: function () { //好友助力页面
    this.setData({
      shareHidden: true
    })
  },
  shareShow: function () {
    this.setData({
      shareHidden: false
    })
  },
  phoneShow: function () { //绑定手机
    this.setData({
      phoneHidden: false
    })
    this.getOutShow()
  },
  codeShow: function () { //小程序邀请码展示
    var that = this;
    if (that.data.codeShow == "height:0rpx;" || that.data.codeShow == "height:0rpx;animation:codeHidden 0.5s;") {
      that.setData({
        codeShow: "height:660rpx;animation:codeShow 0.5s;"
      })
    } else {
      that.setData({
        codeShow: "height:0rpx;animation:codeHidden 0.5s;"
      })
    }
  },
  transmitClose: function () { //代言
    this.setData({
      transmitHidden: true,
      codeShow: "height:0rpx;"
    })
  },
  transmitShow: function () {
    this.setData({
      transmitHidden: false
    })
  },
  guessShow: function () { //猜红包
    this.setData({
      guessHidden: false
    })
  },
  guessClose: function () {
    this.setData({
      guessHidden: true
    })
  },
  fortuneShow: function () { //幸运转盘
    this.setData({
      fortuneHidden: false
    })
  },
  fortuneClose: function () {
    this.setData({
      fortuneHidden: true
    })
  },
  mammonClose: function () { //财神
    this.setData({
      mammonHidden: true
    })
  },
  mammonShow: function () {
    this.setData({
      mammonHidden: false
    })
  },
  goInvite: function () { //跳转邀请列表
    var that = this;
    wx.navigateTo({
      url: '/pages/invite/invite?id=' + that.data.userId,
    })
  },
  goPin:function(){
    var that = this;
    wx.navigateTo({
      url: '/pages/pin/pin',
    })
  },
  goFortune:function(){
    var that = this;
    wx.navigateTo({
      url: '/pages/fortune/fortune',
    })
  },
  circleShow: function () { //水波展示
    var that = this;
    this.setData({
      circleHidden: false
    })
    setTimeout(function () {
      that.setData({
        circleHidden: true,
      })
    }, 4000);
  },
  moneyClose: function () { //提现
    this.setData({
      moneyHidden: true
    })
  },
  moneyShow: function () {
    this.transmitClose();
    this.setData({
      moneyHidden: false
    })
  },
  volumeShow: function () { //兑换
    this.setData({
      volumeCss: "border-bottom: 4rpx solid #F86C2C;",
      dipperCss: "border-bottom: 1rpx solid #FFBB59;",
      volumeHidden: false,
      dipperHidden: true,
    })
  },
  dipperShow: function () {
    this.setData({
      dipperCss: "border-bottom: 4rpx solid #F86C2C;",
      volumeCss: "border-bottom: 1rpx solid #FFBB59;",
      volumeHidden: true,
      dipperHidden: false,
    })
  },
  exchangeClose: function () {
    this.setData({
      exchangeHidden: true
    })
  },
  exchangeShow: function () {
    this.transmitClose();
    this.setData({
      exchangeHidden: false
    })
  },
  ruleClose: function () { //规则
    this.setData({
      ruleHidden: true
    })
  },
  gradeShow: function () {
    this.transmitClose();
    this.setData({
      ruleHidden: false,
      gradeCss: "border-bottom: 4rpx solid #F86C2C;",
      detailedCss: "border-bottom: 1rpx solid #FFBB59;",
      gradeHidden: false,
      detailedHidden: true,
    })
  },
  detailedShow: function () {
    this.transmitClose();
    this.setData({
      ruleHidden: false,
      gradeCss: "border-bottom: 1rpx solid #FFBB59;",
      detailedCss: "border-bottom: 4rpx solid #F86C2C;",
      gradeHidden: true,
      detailedHidden: false,
    })
  },
  helpClose: function () { //砍价窗口
    this.setData({
      helpHidden: true,
    })
  },
  expressUserName: function (e) { //发货
    this.setData({
      expressUserName: e.detail.value
    })
  },
  expressUserPhone: function (e) {
    this.setData({
      expressUserPhone: e.detail.value
    })
  },
  expressUserAddress: function (e) {
    this.setData({
      expressUserAddress: e.detail.value,
    })
  },
  bindRegionChange: function (e) {
    this.setData({
      expressCity: e.detail.value
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    var that = this;
    var url = that.data.apiUrl;
    var wxId = wx.getStorageSync('wxId');
    if (wxId) {
      wx.request({
        url: url + 'api=getUser&wxId=' + wxId + '&shareId=',
        success(loginCode) {
          console.log(loginCode);
          if (loginCode.data.fail == 1) { //登陆成功
            that.setUserShow(loginCode.data);
          }
        },
      })
    }
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
    var that = this;
    console.log("分享链接：" + "pages/paper/paper" + that.data.shareUrl);
    return {
      title: "【完全免费】快来领卷纸，不花钱一个月能兑好几提，不来就亏了！",
      path: "pages/paper/paper" + that.data.shareUrl,
      imageUrl: 'https://www.qiadian.cn/paper/img/papershare.png',
    }
  }
})