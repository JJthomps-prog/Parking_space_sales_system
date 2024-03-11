const app=getApp()
var uid_1=app.globalData.uid;
Page({

    /**
     * 页面的初始数据
     */
    data: {
        Tobepaid:[{}],
        payment:[],
    },
test: function(){
    console.log("xxx")
},
onTapDayWeather: function(e){
    let then=this
    console.log(e)
    wx.showModal({
        title: '提示',
        content: '确认'+"支付吗？",
        success: function (res) { 
          if (res.confirm) {//这里是点击了确定以后
            wx.request({
              url: 'http://127.0.0.1:82/order/zf',
              method:"GET",
              data:{
                "oid":e.currentTarget.dataset.oid,
                "price":e.currentTarget.dataset.price,
                "uid":uid_1
              },
              success(data){
                wx.showToast({
                    title: "支付成功",
                    icon: 'none',
                    duration: 2000
                  })
                  then.getAll()
              }
            })
          } else {//这里是点击了取消以后
            console.log('用户点击取消')
          }
        }
      })

},
goNext(){
  wx.navigateTo({
    url: '../pingjia/index'
  })
},
disonTapDayWeather: function(e){
    console.log(e)
    let then=this
    wx.showModal({
        title: '提示',
        content: '确认'+"取消订单吗？",
        success: function (res) {
          if (res.confirm) {//这里是点击了确定以后
            wx.request({
              url: 'http://127.0.0.1:82/order/delete',
              method:"GET",
              data:{
                "oid":e.currentTarget.dataset.oid,
              },
              success(data){
                then.getAll()
                wx.showToast({
                    title: "取消成功",
                    icon: 'none',
                    duration: 2000
                  })
              }
            })
          } else {//这里是点击了取消以后
            console.log('用户点击取消')
          }
        }
      })
},
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        console.log("data")
this.getAll()
    },

    getAll: function(){
        let then =this
        wx.request({
          url: 'http://127.0.0.1:82/order/getall',
          method: "POST",
          data:{"uid":uid_1
          },
          success(data){
              then.setData({
                Tobepaid:data.data.Tobepaid,
        payment:data.data.payment
                
              })
              console.log(data)
          }
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