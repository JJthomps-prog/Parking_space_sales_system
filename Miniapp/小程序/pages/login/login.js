// pages/login/login.js
Page({

    /**
     * 页面的初始数据
     */
    data: {
        name:"",
        password: "",
        email:"",
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {

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
    emailInput: function(e){
        this.setData({
            email:e.detail.value
        })
    },
    passwordInput: function(e){
        this.setData({
            password:e.detail.value
        })
    },
    login: function(){
        console.log(this.data.email+this.data.password)
        wx.request({
          url: 'http://127.0.0.1:82/api/sql/login',
          method:"POST",
          header: {
            'content-type': 'application/json' 
          },
          data:{
              'email':this.data.email,
              'password':this.data.password,
          },
          success(data){
              console.log(data)
          }
        })
    },
    register: function(){
        if(this.data.email==""||this.data.password==""){
            wx.showModal({
              title: '请输入完整',
            })
        }else{
        wx.request({
            url: 'http://127.0.0.1:82/',
            method:"GET",
            success: function(data){
                console.log(data.data)
            }
          })
    }
    }
})