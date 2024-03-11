// pages/yaohao/yaohao.js
const app=getApp();
Page({
  /**
   * 页面的初始数据
   */
  data: {
      
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    if(getApp().yaohao.ma==0)
    {
      this.setData({ma:0});
    }
    else{
      this.setData({ma:getApp().yaohao.ma})
    }
    if(getApp().yaohao.shuliang==100)
    {
    this.setData({shuliang:100});
    }
    else{
      this.setData({shuliang:getApp().yaohao.shuliang});
    }
    if(getApp().yaohao.bt1==0){
      this.setData({block:"block"});
    }
    else{
      this.setData({block:"none"});
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

  },

  yaohao: function(e){
    wx.showToast({
      title: '摇号成功',
      icon: 'none',
      duration: 2000
    })
    this.setData({block:"none"});
    getApp().yaohao.ma=Math.ceil(Math.random()*100+1);
    this.setData({ma:getApp().yaohao.ma});
    this.setData({shuliang:"99"});
    getApp().yaohao.shuliang=99;
    getApp().yaohao.bt1=1;
  }
})