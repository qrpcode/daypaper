// pages/invite/invite.js
const app = getApp();

Page({

  /**
   * 页面的初始数据
   */
  data: {
    imgUrl: 'https://qiadian.cn/0',
    apiUrl: 'https://www.qiadian.cn/paper/api/api.php?key=pjJ7E6IUodRtpebA&',
    title:"",
    shareUrl:"",
    topList:[],
  },

  goBack:function(){
    var pages = getCurrentPages(); //当前页面
    var beforePage = pages[pages.length - 2]; //前一页
    wx.navigateBack({
      success: function () {
        beforePage.onLoad(); // 执行前一个页面的onLoad方法
      }
    });
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    var url = that.data.apiUrl;
    if(app.globalData.sendShow == true){
      that.setData({
        title:"领纸记录"
      })
    }else{
      that.setData({
        title:"邀请列表"
      })
      if(options){
        if(options.id){
          that.setData({
            bargainId:options.id,
            shareUrl:'k=b' + options.id
          })
          wx.request({
            url: url + 'api=getList&id=' + options.id,
            success:function(res){
              console.log(res.data);
              if(res.data.fail == 1){
                that.setData({
                  topList: res.data.getList,
                })
              }
            }
          })
        }
      }
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
    var that = this;
    return {
      title: "【完全免费】快来领卷纸，不花钱一个月能兑好几提，不来就亏了！",
      path: "pages/paper/paper?" + that.data.shareUrl,
      imageUrl: 'https://www.qiadian.cn/paper/img/papershare.png',
    }
  }
})