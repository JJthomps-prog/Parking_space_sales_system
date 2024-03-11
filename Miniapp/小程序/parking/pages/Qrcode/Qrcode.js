
const app =getApp();
var wxbarcode = require('../../utils/index.js');
  
Page({

  /**
   * 页面的初始数据
   */
  data: {
    touxiang:'',
  },
  onLoad:function(){
    wxbarcode.qrcode('qrcode', 'http://www.baidu.com', 300, 300);
    this.setData({
      touxiang:app.globalData.touxiang
    })
  },
   buyRequest:function(){
    wx.switchTab({
      url: '../../pages/index/index',
    })
   },
})