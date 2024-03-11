// pages/search1/index.js
Page({

    /**
     * 页面的初始数据
     */
    data: {
        value: '',
        parking:[],
        Img:"http://47.113.223.57/assets/images/parkingtest.jpg",
    },
    onSearch(e) {
        console.log(e)
        this.setData({
            value: e.detail,
          });
          let p;
          let then=this; 
        wx.request({
          url: 'http://127.0.0.1:82/api/getParkingLike',
          method: "GET",
          data: {like:e.detail},
          success(data){
              console.log(data)
              p=data.data;
              then.setData({
                  parking:p
              })
            }
          }
        )
      },

      
    /**
     * 生命周期函数--监听页面加载
     */
        onLoad: function (optione) { 
            let p;
            let then=this; 
            wx.request({ 
              url: 'http://127.0.0.1:82/api/getParking',
              method: "GET",
              success: function(data){
                p=data.data;
                then.setData({
                    parking:p
                })
              }
            });
           
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