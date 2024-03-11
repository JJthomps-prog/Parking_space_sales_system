//index.js
//获取应用实例
const app = getApp()
 
Page({
  data: {
    userEmail: '',
    password: ''
  },
  //事件处理函数

  onShow: function () {
    // 生命周期函数--监听页面显示

  },
  onLoad: function () {
   
  },
 
 
  // 获取输入账号 
  usernameInput: function (e) {
    this.setData({
        userEmail: e.detail.value
    })
  },
 
  // 获取输入密码 
  passwordInput: function (e) {
    this.setData({
      password: e.detail.value
    })
  },
 
  // 登录处理
  login: function () {
    var that = this;
    if (this.data.userEmail.length == 0 || this.data.password.length == 0) {
      wx.showToast({
        title: '账号或密码不能为空',
        icon: 'none',
        duration: 2000
      })
    } else {
      wx.request({
        url:"http://127.0.0.1:82/api/sql/login", 
        method: 'POST',
        data: {
          'email':this.data.userEmail,
          'password':this.data.password,
        },
        header: {
          "Content-Type":"application/json"
        },
        success(res) {
            console.log(res)
          if (res.data.code == 400) {
            wx.showToast({
                title: "邮箱或密码错误",
                icon: 'none',
                duration: 2000
              })
          }else if(res.data.code == 400){
            wx.showToast({
                title: "登录失败，原因请上官网查看",
                icon: 'none',
                duration: 2000
              })
          }
           else {
            app.globalData.uid=res.data.id;
            app.globalData.email=res.data.email;
            app.globalData.name=res.data.name;
            wx.switchTab({ 
              url: '../index/index',
            })
          }
        }
      })
    }
  }
})
 