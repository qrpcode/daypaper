// pages/fortune/fortune.js
const app = getApp()

Page({
  /**
   * 页面的初始数据
   */
  data: {
    //幸运抽奖
    fortuneHidden: true,
    fortuneCss: "transform:rotate(3deg);", //animation: fortune 2s;
    getFortuneClick: "getFortune",

    apiUrl: app.globalData.apiUrl,
    pddapi: app.globalData.pddapi,

    //奖励提醒
    tipHidden: true,
    tipPic: "pfget1.gif",
    tipText: "",
    tipType: 0,

    //现有奖励
    pWeatherHidden: true,
    pPinHidden: true,
    pNeiHidden: true,

    //今日奖励
    getHidden: true
  },

  getFortune: function () {
    var that = this;
    var url = that.data.apiUrl;
    that.setData({
      getFortuneClick: ""
    })
    wx.request({
      url: url + 'api=getFortune&wxId=' + wx.getStorageSync('wxId'),
      success: function (res) {
        console.log(res);
        if (res.data.fail == 1) {
          if (res.data.addWater == 100) {
            that.setData({
              fortuneCss: "transform:rotate(1265deg);animation: fortune 2.5s;",
              getFortuneClick: "getFortune",
            })
          }
          if (res.data.addWater == 300) {
            that.setData({
              fortuneCss: "transform:rotate(1109deg);animation: fortune 2.5s;",
              getFortuneClick: "getFortune",
            })
          }
          if (res.data.addWater == 466) {
            that.setData({
              fortuneCss: "transform:rotate(1317deg);animation: fortune 2.5s;",
              getFortuneClick: "getFortune",
            })
          }
          if (res.data.addWater == 600) {
            that.setData({
              fortuneCss: "transform:rotate(1161deg);animation: fortune 2.5s;",
              getFortuneClick: "getFortune",
            })
          }
          if (res.data.addWater == 0) {
            that.setData({
              fortuneCss: "transform:rotate(1213deg);animation: fortune 2.5s;",
              getFortuneClick: "getFortune",
            })
          }
          setTimeout(function () {
            that.getTips(res.data.addWater);
            that.getFortuneDo();
          }, 3000);
          getApp().globalData.fortuneNum = app.globalData.fortuneNum - 1;
          that.setData({
            fortuneHave: app.globalData.fortuneNum
          })
        }
        if (res.data.fail == -1) {
          that.tips("您今天已经领过啦");
          that.getFortuneDo();
        }
      }
    })
  },
  tips: function (text) { //全局提示
    wx.showToast({
      title: text,
      icon: 'none',
      duration: 2500
    })
  },
  getFortuneDo: function () {
    this.setData({
      fortuneCss: "",
      getFortuneClick: "getFortune",
    })
  },

  tipsClose: function () {
    this.setData({
      tipHidden: true
    })
  },
  goGet1: function () {
    this.setData({
      tipType: 1
    })
    this.tipsGo()
  },
  goGet2: function () {
    this.setData({
      tipType: 2
    })
    this.tipsGo()
  },
  goGet3: function () {
    this.setData({
      tipType: 3
    })
    this.tipsGo()
  },
  tipsGo: function () {
    var that = this;
    if (that.data.tipType == 0) {
      that.tipsClose();
    }
    if (that.data.tipType == 1) {
      wx.request({
        url: that.data.pddapi + 'api=pdd.ddk.rp.prom.other&channel_type=3&p_id=1013606_128905068&custom_parameters=' + wx.getStorageSync('wxId'),
        success(res) {
          console.log(res)
          wx.navigateToMiniProgram({
            appId: 'wx32540bd863b27570',
            path: res.data.rp_promotion_url_generate_response.url_list[0].we_app_info.page_path,
          })
        }
      })
      that.tipsClose();
    }
    if (that.data.tipType == 2) {
      wx.navigateToMiniProgram({
        appId: 'wx760d2513080f80c8',
        path: "pages/home/home",
      })
      that.tipsClose();
    }
    if (that.data.tipType == 3) {
      wx.request({
        url: that.data.pddapi + 'api=pdd.ddk.rp.prom.other&channel_type=5&p_id=1013606_128905068&custom_parameters=' + wx.getStorageSync('wxId'),
        success(res) {
          console.log(res)
          wx.navigateToMiniProgram({
            appId: 'wx32540bd863b27570',
            path: res.data.rp_promotion_url_generate_response.url_list[0].we_app_info.page_path,
          })
        }
      })
      that.tipsClose();
    }
  },
  getClose: function () {
    this.setData({
      getHidden: true
    })
  },
  getShow: function () {
    this.setData({
      getHidden: false
    })
  },

  getTips: function (num) {
    if (num == 466) {
      this.setData({
        tipPic: "pfget3.png",
        tipText: "获得466木浆",
        tipType: 0,
        tipHidden: false
      })
    }
    if (num == 300) {
      this.setData({
        tipPic: "pfget4.png",
        tipText: "获得300木浆",
        tipType: 0,
        tipHidden: false
      })
    }
    if (num == 600) {
      this.setData({
        tipPic: "pfget5.png",
        tipText: "获得600木浆",
        tipType: 0,
        tipHidden: false
      })
    }
    if (num == 100) {
      this.setData({
        tipPic: "pfget6.png",
        tipText: "获得100木浆",
        tipType: 0,
        tipHidden: false
      })
    }
    if (num == 0) {
      var that = this;
      //今天的时间
      var day2 = new Date();
      day2.setTime(day2.getTime());
      var s = day2.getFullYear() + "-" + (day2.getMonth() + 1) + "-" + day2.getDate();

      var pPin = false;
      var pWeather = false;
      var pBuy = false;

      if (wx.getStorageSync('pPin')) {
        if (wx.getStorageSync('pPin') != s) {
          pPin = true;
        } else {
          if (wx.getStorageSync('pWeather')) {
            if (wx.getStorageSync('pWeather') != s) {
              pWeather = true;
            } else {
              if (wx.getStorageSync('pBuy')) {
                if (wx.getStorageSync('pBuy') != s) {
                  pBuy = true;
                }
              } else {
                pBuy = true;
              }
            }
          } else {
            pWeather = true;
          }
        }
      } else {
        pPin = true;
      }
      if (pPin == true) {
        wx.setStorageSync('pPin', s)
        that.setData({
          tipPic: "pfget1.gif",
          tipText: "无门槛红包",
          tipType: 1,
          tipHidden: false,
          pPinHidden: false,
        })
      }
      if (pWeather == true) {
        wx.setStorageSync('pWeather', s)
        that.setData({
          tipPic: "pfget2.gif",
          tipText: "1元现金快来领",
          tipType: 2,
          tipHidden: false,
          pWeatherHidden: false
        })
      }
      if (pBuy == true) {
        wx.setStorageSync('pBuy', s)
        that.setData({
          tipPic: "pfget7.gif",
          tipText: "一等奖专属",
          tipType: 3,
          tipHidden: false,
          pNeiHidden: false
        })
      }
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

    //今天的时间
    var day2 = new Date();
    day2.setTime(day2.getTime());
    var s = day2.getFullYear() + "-" + (day2.getMonth() + 1) + "-" + day2.getDate();

    console.log(wx.getStorageSync('pPin'))
    if (wx.getStorageSync('pPin')) {
      if (wx.getStorageSync('pPin') == s) {
        this.setData({
          pPinHidden: false,
        })
      }
    }
    console.log(wx.getStorageSync('pWeather'))
    if (wx.getStorageSync('pWeather')) {
      if (wx.getStorageSync('pWeather') == s) {
        this.setData({
          pWeatherHidden: false,
        })
      }
    }
    console.log(wx.getStorageSync('pBuy'))
    if (wx.getStorageSync('pBuy')) {
      if (wx.getStorageSync('pBuy') == s) {
        this.setData({
          pNeiHidden: false,
        })
      }
    }
    this.setData({
      fortuneHave: app.globalData.fortuneNum
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

  }
})