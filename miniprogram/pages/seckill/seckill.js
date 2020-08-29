// pages/seckill/seckill.js
const app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    apiUrl: app.globalData.apiUrl,
    list: [],
    seckillBtnCss: "url(https://*****/blackbutton.png)",
    seckillBtnText: "下一场点开始"
  },
  setList:function(){
    var that = this;
    var list = [];
    var name = ["🍇","🍒","🍑","人","变","身","开","心","超","变","身","没","理","由","放","不","下","你","的","笑","珴","有","旳","不","多","就","爱","迩","旳","心","情","深","才","会","久","伴","你","时","光","不","会","辜","负","你","虚","伪","终","究","会","被","发","现","见","血","封","侯","的","女","人","那","仅","存","的","骄","傲","早","已","失","去","如","果","我","说","一","切","都","是","假","的","🍏","裆","有","货","不","信","试","试","口","有","料","不","信","摸","摸","🍉","调","控","那","谁","我","耐","你","我","耐","你","那","谁","妩","媚","而","失","苍","劲","对","于","他","我","只","是","另","一","种","感","情","摩","羯","座","太","阳","太","耀","眼","月","亮","太","冰","🍊","心","的","我","哭","泣","的","你","J","🍐","C","K","k","i","s","s","H","E","L","🍍","N","倾","述","聆","听","我","们","开","开","心","心","我","们","快","快","乐","乐","暧","昧","是","涐","的","本","性","疯","疯","癫","癫","不","代","表","莪","随","随","便","便","倾","听","你","所","有","心","声","红","颜","怒","荝","倾","天","下","你","一","直","活","在","我","的","记","忆","中","直","被","🍎","仿","未","🍌","超","越","惯","一","个","人","独","自","坐","在","窗","前","嘴","角","掀","起","一","丝","苍","笑","嘴","角","勾","起","一","丝","苦","笑","因","为","有","了","因","为","所","以","才","有","所","以","後","雨","没","有","脚","步","声","回","到","原","点","却","回","不","到","原","来","陪","你","到","永","远","随","你","🍋","天","边","有","钱","要","懂","得","假","装","没","钱","要","懂","得","包","装","姐","瘦","但","彪","悍","穿","过","指","缝","透","射","的","阳","光","宁","愿","失","恋","亦","不","想","失","礼","往","事","如","烟","岁","月","如","歌","如","梦","如","幻","雨","露","心","🍈","上","校","门","卫","网","上","💐","邻","临","窗","观","🌵","素","子","花","开","封","情","舞","韵","归","去","如","风","小","小","水","马","一","品","白","🌷","森","林","散","布","孤","峰","无","伴","漂","亮","冰","🌾","水","清","天","蓝","邀","月","对","影","无","门","有","缘","凌","波","微","步","闲","云","清","烟","香","椿","丛","林","灵","慧","可","人","和","风","戏","雨","深","秋","无","痕","阳","光","的","舟","春","天","的","吻","清","月","无","梦","迷","鸟","归","林","金","色","枫","叶","花","自","芬","芳","八","枝","玫","瑰","别","样","别","样","枫","叶","落","落","英","英","快","乐","北","凤","男","飞","老","车","新","路","雾","里","看","吧","网","间","散","布","梅","芳","竹","清","袅","袅","亭","亭","从","雾","中","来","溪","施","柔","情","小","💮","毒","药","投","鼠","忌","器","野","渡","无","人","🌺","🌼","八","仙","之","人","义","🌻","容","辞","海","誓","山","盟","行","侠","仗","义","🌸","小","刺","猬","微","笑","听","雨","万","年","白","狐","玉","雪","飘","飘","姻","脂","花","开","墨","梅","紫","云","迪","威","墨","山","乡","人","漓","江","渔","火","洛","水","渔","翁","飞","天","蝈","蝈","🌹","世","晨","晓","小","沙","尘","瀑","本","来","无","物","呼","儿","咳","呀","顺","风","旋","子","逆","向","锤","头","布","衣","小","贩","三","羊","开","菜","草","原","寻","梦","落","叶","红","秋","蝶","舞","芳","香","独","立","寒","秋","西","北","虎","世","纪","精","灵","长","啸","当","歌","长","乐","兴","奋","咫","尺","幸","福","蓝","蓝","的","天","志","善","志","美","走","向","春","天"];
    var date = new Date();
    var hour = date.getHours() - 10;
    if(hour > 10){hour = 10}
    console.log(hour)
    var number = ((date.getDate() * 66 + ((date.getMonth() + 1) * 320) + date.getDay() * 129 + 1)% (50 * hour)) + hour * 750;
    for(var i = 1; i <= 6; i++){
      var listOnce = [];
      var imgId = (33 * i + date.getDate())%200 + 1; 
      var imgString = "";
      if(imgId < 100){
        imgString = (Array(2).join(0) + imgId).slice(-2);
      }else{
        imgString = (Array(3).join(0) + imgId).slice(-3);
      }
      var nameId = (date.getMonth() + 1 + date.getDate())*(date.getDay() + 1 + i) % 643;
      var nameId2 = (date.getMonth() + 1 + date.getDay() + 1)*(date.getDate() + i) % 643;
      var userName = name[nameId] + name[nameId + 1] + "***" + name[nameId2];
      number = number - ((i - 1) * (((date.getDay() + 1) * 50 + date.getDate() * 58) % 60 + 1));
      if(number < 1){
        number = 0;
        imgString = "Img",
        userName = "等待参与"
      }
      listOnce = {
        pic: imgString,
        name: userName,
        number: number
      }
      list.push(listOnce);
    }
    that.setData({
      list: list
    })
    console.log(list)
  },

  // mark: 定时器
  timeShow: function(time){
    var that = this;
    var logCountDown = setInterval(function () {
      var date = new Date();
      var hour = date.getHours();
      var minute = date.getMinutes();
      if(minute > 20){
        that.setData({
          seckillBtnCss: "url(https://*****/blackbutton.png)",
        })
        if(hour >= 21){
          that.setData({
            seckillBtnText: "明早6点开始"
          })
        }else if(hour < 6){
          that.setData({
            seckillBtnText: "下一场6点开始"
          })
        }else{
          that.setData({
            seckillBtnText: "下一场" + (hour + 1) + "点开始"
          })
        }
      }else{
        if(hour > 21){
          that.setData({
            seckillBtnText: "明早6点开始",
            seckillBtnCss: "url(https://*****/blackbutton.png)",
          })
        }else if(hour < 6){
          that.setData({
            seckillBtnText: "下一场6点开始",
            seckillBtnCss: "url(https://*****/blackbutton.png)",
          })
        }else{
          var seckill = wx.getStorageSync('seckill');
          if(seckill == date.getDate() + "-" + hour){
            //本轮已经参与
            that.setData({
              seckillBtnText: "下一场" + (hour + 1) + "点开始",
              seckillBtnCss: "url(https://*****/blackbutton.png)",
            })
          }else{
            that.setData({
              seckillBtnCss: "none",
              seckillBtnText: ""
            })
          }
        }
      }
    }, 100)
  },

  //秒杀
  seckillDo:function(){
    var that = this;
    if(that.data.seckillBtnText == ""){
      that.setData({
        seckillBtnCss: "url(https://*****/blackbutton.png)",
        seckillBtnText: "下一场 点开始"
      })
      var date = new Date();
      wx.setStorageSync('seckill', date.getDate() + "-" + date.getHours());
      //发送请求
      var url = "";
      if(wx.getStorageSync('numPercentage') > 1000){
        url = that.data.apiUrl + 'seckill/paper';
      }else{
        url = that.data.apiUrl + 'seckill/water';
      }
      wx.request({
        url: url,
        data: {
          token: wx.getStorageSync('token'),
        },
        header: {
          'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
        },
        method: "POST",
        success(res){
          console.log(res)
          if(res.data.fail == -1 || res.data.fail == -2){
            wx.showToast({
              title: "礼品都送完了，下一时段再试试~",
              icon: 'none',
              duration: 2500
            })
          }else if(res.data.fail == -3){
            wx.showToast({
              title: "现在不在抢购时间哟~",
              icon: 'none',
              duration: 2500
            })
          }else if(res.data.addWater > 0){
            wx.showToast({
              title: "手气不错！抢到" + res.data.addWater + "纸浆~",
              icon: 'none',
              duration: 2500
            })
          }else if(res.data.expressKey != ""){
            wx.showToast({
              title: "恭喜获得卷纸直发券！重进小程序可见入口~",
              icon: 'none',
              duration: 4000
            })
          }else{
            wx.showToast({
              title: "活动太挤了，下次一定能抢到~",
              icon: 'none',
              duration: 2500
            })
          }
        }
      })
    }else{
      wx.showToast({
        title: "现在不能领取哦~",
        icon: 'none',
        duration: 2500
      })
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setList();
    this.timeShow();
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
    var url = "pages/paper/paper?k=n" + wx.getStorageSync('userId');
    return {
      title: "【完全免费】快来养卷纸，不花钱一个月能兑好几提，不来就亏了！",
      path: url,
      imageUrl: 'https://www.*****/paper/img/papershare.png',
    }
  }
})