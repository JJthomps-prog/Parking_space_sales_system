// pages/category/index.js
Page({

    /**
     * 页面的初始数据
     */
    data: {
        gouxuan:"",
        parking:[],
        Img:"http://127.0.0.1:82/assets/images/parkingtest.jpg",
        option1: [
            { text: '全部状态', value:0 },
            { text: '未开始认筹', value:1 },
            { text: '已开始认筹', value:2 },
            { text: '未开盘', value:3 },
            { text: '已开盘', value:4 },
          ],        
          option2: [
            { text: '默认排序', value: 'a' },
            { text: '由低到高', value: 'b' },
            { text: '由高到低', value: 'c' },
          ],
          option3:[
            { text:'全部区域',value :'x'},
            { text:'杭州',value :'z'},
          ],
          value1: 0,
          value2: 'a',
          value3:'x',
          title1:'开盘状态',
          title2:'价格',
          title3:'区域',
    },

    getOpen: function(e){
      this.setData({
        value1:e.detail
      })
      if(this.data.value1==1||this.data.value1==2){
      }else{
      this.getOpenAndPrice(this.data.value1,this.data.value2);
      }
    },
    getPrice: function(e){
      this.setData({
        value2:e.detail
      })
      this.getOpenAndPrice(this.data.value1,this.data.value2);
    },
    getOpenAndPrice: function(value1,value2){
      let then=this; 
      let p;
      wx.request({
        url: 'http://127.0.0.1:82/api/searchs',
        method: "POST",
        data:{
          "open":value1,
          "price":value2
        },
        success(data){
          p=data.data;
          then.setData({
              parking:p
          })
        }
      })
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