const app=getApp()
var uid_1=app.globalData.uid;
Page({

    /**
     * 页面的初始数据
     */
    data: {
        parking_lot:{"city":"杭州市",
                     "address":"",
                     "price":0.0,
                     "id":0,
                     "pid":0,
                     "open":0,},
                     number:"",
                     explain:"",
        showShare: false,
        options: [
            { name: '微信', icon: 'wechat', openType: 'share' },
            { name: '微博', icon: 'weibo' },
            { name: '复制链接', icon: 'link' },
            { name: '分享海报', icon: 'poster' },
            { name: '二维码', icon: 'qrcode' },
          ],
        activeNames: ['1'],
        indicatorDots: true,
        autoplay: true,  
        interval: 3000,   
        duration: 1000,  
        imgUrls: [
            'https://wx2.sinaimg.cn/mw690/006qbn5yly1gtwjv9jsm8j30ly087gmb.jpg',
            'https://wx2.sinaimg.cn/mw690/006qbn5yly1gtwjvd9v0mj30l008g0t4.jpg',
            'https://wx4.sinaimg.cn/mw690/006qbn5yly1gtwjvbf6umj30k607ujsc.jpg'
          ],
          text:"",
          openText:""
    },

    onchange(event) {
        wx.showToast({
          title: `切换到标签 ${event.detail.name}`,
          icon: 'none',
        });
      },
      details_msg(){
        wx.navigateTo({
          url: '../details_msg/details_msg'
        })
      },
      tels: function () {
        wx.makePhoneCall({
            phoneNumber: '18888888888',
            success: function () {
                console.log("拨打电话成功！")
            },
            fail: function () {
                console.log("拨打电话失败！")
            }
        })
      },
      onClick(event) {
        this.setData({ showShare: true });
      },
      onSelect(event) {
        Toast(event.detail.name);
        this.onClose();
      },
      onClose() {
        this.setData({ show1: false });
        this.setData({ showShare: false });
      },
      onChange(event) {
        this.setData({
          activeNames: event.detail,
        });
      },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) { 
        this.setData({
          parking_lot:options
        }) 
        if(options.open==0){
          this.setData({
            text:"认筹",
            openText:"待开盘"
          }) 
        }else if(options.open==1){
          this.setData({
            text:"加入购物车",
            openText:"已开盘"
          }) 
        }
        this.getOneP(options.id)
    },

    getOneP: function(id){
      let then=this;
      wx.request({
        url: 'http://127.0.0.1:82/api/getOneP',
        method:"GET",
        data:{"id":id},
        success(data){
          console.log(data)
          then.setData({
            explain:data.data.explain,
            number:data.data.number,
          })
        }
      })
    },

    onVerify: function(){
      let then=this;
      wx.showModal({
        title: '提示',
        content: '确认'+then.data.text+"吗？",
        success: function (res) {
          if (res.confirm) {//这里是点击了确定以后
            wx.request({
              url: 'http://127.0.0.1:82/up/user',
              method:"POST",
              data:{
                "uid":uid_1,
                "open":then.data.parking_lot.open,
                "plid":then.data.parking_lot.id
              },
              success(data){
                console.log(data);
                wx.showToast({
                  title: data.data,
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