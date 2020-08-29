// app.js 
const ald = require('./utils/ald-stat.js')
App({
  onLaunch: function (options) {
    const updateManager=wx.getUpdateManager(); 
    updateManager.onCheckForUpdate(function(res){
        if(res.hasUpdate){
            updateManager.onUpdateReady(function(){
                wx.showModal({
                    title:'更新提示',
                    content:'新版本已经准备好，点击确定重新启动',
                    showCancel:false,
                    success:res=>{
                        if(res.confirm){
                            updateManager.applyUpdate();
                        }
                    }
                })
            })
            updateManager.onUpdateFailed(function(){
                wx.showModal({
                    title:'提示',
                    content:'检查到有新版本，但是下载失败，请检查网络设置',
                    showCancel:false
                })
            })
        }
    })
  },
  globalData: {
    sendShow:false,
    pddapi: "*****",
    apiUrl: '"*****",',
    getCodeApi: "*****",

    fortuneNum: 0,
    store: 1
  }
})