// pages/bargain/bargain.js
const app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    bargainTips: true,
    apiUrl: app.globalData.apiUrl,
    priceDown: 0.00,
    hour: "",
    minute: "",
    second: "",
    millisecond: "",
    priceLine: 0,
    priceDownLine: 0,
    helpList: []
  },

  newBargain: function(){
    var that = this;
    that.setData({
      bargainTips: true
    })
    wx.request({
      url: that.data.apiUrl + 'bargain/newBargain',
      data: {
        token: wx.getStorageSync('token'),
        itemType: "1"
      },
      header: {
        'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
      },
      method: "POST",
      success(bargain){
        console.log(bargain);
        wx.setStorageSync('bargainId', bargain.data.id);
        that.getBargain(bargain.data.id);
      }
    })
  },

  getBargain(id){
    var that = this;
    wx.request({
      url: that.data.apiUrl + 'bargain/getBargain',
      data: {
        token: wx.getStorageSync('token'),
        id: id
      },
      header: {
        'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
      },
      method: "POST",
      success(bargain){
        console.log(bargain)
        that.setData({
          priceLast: bargain.data.itemDown.toFixed(2),
          priceDown: bargain.data.itemNow.toFixed(2),
          priceLine: bargain.data.itemNow/bargain.data.itemPrice * 645,
          priceDownLine: bargain.data.itemNow/bargain.data.itemPrice * 220 + 300,
        })
        var helpList = bargain.data.helpList;
        var tipsList = ["看我一阳指，点点点", "小李飞刀，刀出必中，咻！", "吃我一招九阴白骨点，嘿嘿~", "看我大力金刚指威力如何", "手一滑，下次肯定更多"];
        for(var i = 0; i < helpList.length; i++){
          if(helpList[i].downPrice == 0.01 || helpList[i].downPrice == 0.02){
            var num = 0 + Math.round(Math.random() * 5);
            helpList[i].tips = tipsList[num];
          }
        }
        that.setData({
          helpList: helpList
        })
        that.timeShow(bargain.data.lastTime);
      }
    })
  },

  // mark: 定时器
  timeShow: function(time){
    var that = this;
    var millisecond = 9;
    var logCountDown = setInterval(function () {
      that.setData({
        hour: (Array(2).join(0) + parseInt(time/3600)).slice(-2),
        minute: (Array(2).join(0) + parseInt(time%3600/60)).slice(-2),
        second: (Array(2).join(0) + parseInt(time%60)).slice(-2),
        millisecond: millisecond
      })
      millisecond = millisecond - 1;
      if(millisecond == -1){
        millisecond = 9;
        time = time - 1;
      }
    }, 100)
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log(options);
    var that = this;
    var bargainId = wx.getStorageSync('bargainId');
    if(bargainId == 0){
      that.setData({
        bargainTips: false
      })
    }else{
      that.getBargain(bargainId);
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
    var url = "pages/paper/paper?k=b" + wx.getStorageSync('bargainId');
    return {
      title: "【帮我砍价】快来养卷纸，不花钱一个月能兑好几提，不来就亏了！",
      path: url,
      imageUrl: 'https://www.*****/paper/img/papershare.png',
    }
  }
})