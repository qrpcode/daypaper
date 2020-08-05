// pages/pin/pin.js
var app = getApp();

Page({

  /**
   * 页面的初始数据
   */
  data: {
    pddapi: app.globalData.pddapi,
    phone: 0,
    sorryHidden: true,

    item: [],
    page: 1,
    keyword: "",
    inputValue: "",
    placeText: "搜索关键词或粘贴商品标题",
    inputDisable: false,
    keywordHidden: true,
    type: 0,

    show: false,
    getHidden: true,

    lastTime:"",

    typeCss: ['-choose'],
    typeId: 0,
    goTopHidden: true,

    getLogpdd: true
  },

  //上传用户拼多多
  getLogPdd: function () {
    this.setData({
      getLogpdd: true
    })
    var that = this;
    setTimeout(function () {
      if(that.data.getLogpdd == true){
        var apiUrl = app.globalData.apiUrl;
        var wxId = wx.getStorageSync('wxId');
        wx.request({
          url: apiUrl + 'api=getStore&wxId=' + wxId,
          success(res) {
            console.log(res)
            if (res.data.fail == 1) {
              wx.showToast({
                title: "获得" +  res.data.addWater + "金币",
                icon: 'success',
                duration: 1500
              })
              getApp().globalData.fortuneNum = app.globalData.fortuneNum - 1;
            }
          }
        })
      }
    }, 60000)
  },

  //拼多多推荐
  getItem: function () {
    var that = this;
    var url = that.data.pddapi;
    var item = that.data.item;
    wx.request({
      url: url + 'api=pdd.ddk.goods.search.nomal&page=' + that.data.page,
      success(res) {
        console.log(res)
        var returnList = res.data.goods_search_response.goods_list;
        that.setData({
          item: item.concat(that.setReturn(returnList)),
          page: that.data.page + 1,
          keyword: "",
          inputValue: ""
        })
      }
    })
  },

  //首页被选择
  chooseHome: function(){
    var that = this;
    that.cleanWord();
    that.setData({
      item: [],
      page: 1,
      typeCss: ['-choose'],
      typeId: 0,
    })
    this.getItem();
  },

  //分类被选择
  chooseType: function (date) {
    var that = this;
    that.cleanWord();
    var url = that.data.pddapi;
    if (date) {
      var opt_id = date.currentTarget.dataset.optid;
      var cssList = new Array(opt_id + 1);
      for (var index = 0; index < (opt_id + 1); index++) {
        cssList[index] = "";
      }
      cssList[opt_id] = '-choose';

      that.setData({
        typeCss: cssList,
        page: 1,
        item: [],
        typeId: opt_id
      })
    } else {
      var opt_id = that.data.typeId
    }

    wx.request({
      url: url + 'api=pdd.ddk.goods.search.together&opt_id=' + opt_id + '&page=' + that.data.page,
      success(res) {
        console.log(res);
        var returnList = res.data.goods_search_response.goods_list;
        that.setData({
          item: that.data.item.concat(that.setReturn(returnList)),
          page: that.data.page + 1,
          keyword: "",
          inputValue: ""
        })
      }
    })
    that.setData({
      homeItemGeting: false,
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
    if(returnList.promotion_num > 1.2){
      returnList.hidden = false
      returnList.paperNum = 1
    }
    if(returnList.promotion_num > 2.4){
      returnList.hidden = false
      returnList.paperNum = 2
    }
    returnList.coupon_discount = returnList.coupon_discount*0.01;
    return returnList;
  },

  //去拼多多拼单
  goBuy: function (res) {
    var that = this;
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

  //获取输入内容
  getInputValue(e) {
    this.setData({
      keyword: e.detail.value
    })
  },
  //搜索商品
  seach: function () {
    var that = this;
    var keyword = that.data.keyword;
    var url = that.data.pddapi;

    if ((keyword.search("会员") != -1 || keyword.search("录制") != -1 || keyword.search("视频") != -1 || keyword.search("壁纸") != -1 || keyword.search("VIP") != -1 || keyword.search("vip") != -1 || keyword.search("卡券") != -1 || keyword.search("QQ") != -1 || keyword.search("付费") != -1 || keyword.search("账号") != -1 || keyword.search("代练") != -1 || keyword.search("远程") != -1 || keyword.search("腾讯视频") != -1 || keyword.search("优酷") != -1 || keyword.search("爱奇艺") != -1 || keyword.search("知乎") != -1 || keyword.search("百度") != -1 || keyword.search("烟") != -1)) {
      that.setData({
        sorryHidden: false,
        inputValue: "",
      })
    } else {
      if (that.data.type == 0) {
        that.setData({
          page: 1,
          item: [],
          type: 1,
          sorryHidden: true,
        })
      }
      var item = that.data.item;
      wx.request({
        url: url + 'api=pdd.ddk.goods.search.nomal&page=' + that.data.page + '&keyword=' + keyword,
        success(res) {
          console.log(res)
          var returnList = res.data.goods_search_response.goods_list;
          that.setData({
            item: item.concat(that.setReturn(returnList)),
            page: that.data.page + 1,
            inputValue: "",
            placeText: "",
            inputDisable: true,
            keywordHidden: false,
          })
        }
      })
    }
  },


  goTop: function (e) { // 一键回到顶部
    if (wx.pageScrollTo) {
      wx.pageScrollTo({
        scrollTop: 0
      })
    } else {
      wx.showModal({
        title: '提示',
        content: '当前微信版本过低，无法使用该功能，请升级到最新微信版本后重试。'
      })
    }
  },
  
  //清空搜索词
  cleanWord:function(){
    this.setData({
      item: [],
      page: 1,
      keyword: "",
      inputValue: "",
      placeText: "搜索关键词或粘贴商品标题",
      inputDisable: false,
      keywordHidden: true,
      type: 0,
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    that.setData({
      show: true
    })
    this.getItem()
    this.getLogPdd();
    wx.getSystemInfo({
      success(res) {
        console.log(res.model)
        var phone = res.model;
        if (phone.search("iPhone") != -1 || phone.search("iPad") != -1 || phone.search("Mac") != -1) {
          that.setData({
            phone: 1
          })
        }
      }
    })
  },
  onPageScroll: function (e) { // 获取滚动条当前位置
    var that = this;
    if (e.scrollTop > 600 && that.data.goTopHidden == true) {
      this.setData({
        goTopHidden: false
      })
    }
    if (e.scrollTop <= 600 && that.data.goTopHidden == false) {
      this.setData({
        goTopHidden: true
      })
    }
  },
  //返回顶部
  goTop: function (e) { // 一键回到顶部
    if (wx.pageScrollTo) {
      wx.pageScrollTo({
        scrollTop: 0
      })
    } else {
      wx.showModal({
        title: '提示',
        content: '当前微信版本过低，无法使用该功能，请升级到最新微信版本后重试。'
      })
    }
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
    this.setData({
      getLogpdd: false
    })
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
    this.setData({
      getLogpdd: false
    })
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
    var that = this;
    if (that.data.type == 0) {
      that.getItem()
    } else {
      that.seach()
    }
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
    if(app.globalData.special == true){
      return {
        title: app.globalData.getLogText.special_title,
        path: "pages/home/home?s=" + app.globalData.member_id,
        imageUrl: app.globalData.getLogText.special_image,
      }
    }else{
      return {
        title: app.globalData.getLogText.share_title,
        path: "pages/home/home?s=" + app.globalData.member_id,
        imageUrl: app.globalData.getLogText.share_image,
      }
    }
  }
})