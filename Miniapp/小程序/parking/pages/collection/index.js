const app=getApp()
console.log(app.globalData)
var uid_1=app.globalData.uid;
console.log(uid_1);
Page({

    /**
     * 页面的初始数据
     */
    data: {
        cart:[
        //     {
        //     goods_id:1,
        //     checked:0,
        //     address:"",
        //     number:"",
        //     price:10000
        // },
        // {
        //     goods_id:2,
        //     checked:0,
        //     price:21111
        // }
        ],   
        totalPrice:0,
        allchecked:0
    },

    handleItemChange: function(e){
        let id=e.currentTarget.dataset.id
        let index=e.currentTarget.dataset.index
        let c=this.data.cart[index].checked
        let totalPrice=this.data.totalPrice
        let price=this.data.cart[index].price
        var checked = "cart["+index+"].checked"
        if(c==0){
            this.setData({
                [checked]:1,
                totalPrice:totalPrice+price
              })
        }else{
            this.setData({
                [checked]:0,
                totalPrice:totalPrice-price
              })
        }

        let s=0;
        for(let i=0;i<this.data.cart.length;i++){
            if(this.data.cart[i].checked==1){
                s++;
            }
        }
        if(s==this.data.cart.length){
            this.setData({
                allchecked:1
              })
        }else{
            this.setData({
                allchecked:0
              })
        }


    },
    handleItemAllchange:function(e){
        let then =this;
        console.log(this.data.allchecked)
        let index=this.data.cart.length;
        let totalPrice=0;
        if(this.data.allchecked==0){
            this.setData({
                allchecked:1
            })
            for(let i=0;i<index;i++){
                totalPrice=totalPrice+this.data.cart[i].price
                 if(this.data.cart[i].checked==0){            
                    var checked = "cart["+i+"].checked"
                    this.setData({
                        [checked]:1,
                        totalPrice:totalPrice
                      })
                 }
            }
        }else{
            this.setData({
                allchecked:0
            })
            for(let i=0;i<index;i++){
                 if(this.data.cart[i].checked==1){            
                    var checked = "cart["+i+"].checked"
                    this.setData({
                        [checked]:0,
                        totalPrice:0
                      })
                 }
            }
        }
    },

    delete: function(){
        let then =this;
        wx.showModal({
            title: '提示',
            content: "确认删除吗？",
            success: function (res) {
              if (res.confirm) {
                  let list=[];
                  list[0]=app.globalData.uid
                  let i=1;
                  for(let j=0;j<then.data.cart.length;j++){
                      if(then.data.cart[j].checked==1){
                        list[i]=then.data.cart[j].goods_id
                        i++
                      }
                  }
                  wx.request({
                    url: 'http://127.0.0.1:82/cart/del',
                    method:"POST",
                    data:list,
                    success(data){
                        console.log(data)
                    }
                  })
                  wx.showToast({
                    title: "删除成功",
                    icon: 'none',
                    duration: 2000
                  })
                  then.getCart()
                  then.setData({
                      totalPrice:0
                  })
              } else {//这里是点击了取消以后
                console.log('用户点击取消')
              }
            }
          })
    },

    handlePay: function(){
        let then =this;
        wx.showModal({
            title: '提示',
            content: "确认支付"+then.data.totalPrice+"元吗？",
            success: function (res) {
              if (res.confirm) {
                let list=[];
                list[0]=uid_1
                list[1]=then.data.totalPrice
                let i=2;
                for(let j=0;j<then.data.cart.length;j++){
                    if(then.data.cart[j].checked==1){
                      list[i]=then.data.cart[j].goods_id
                      i++
                    }
                    console.log(list)
                }
                wx.request({
                    url: 'http://127.0.0.1:82/cart/order',
                    method:"POST",
                    data:list,
                    success(data){
                        console.log(data)
                        if(data.data==-1){
                            wx.showToast({
                                title: "购买失败",
                                icon: 'none',
                                duration: 2000
                              })
                        }else{
                            wx.showToast({
                                title: "购买成功",
                                icon: 'none',
                                duration: 2000
                              })
                        }
                    }
                  })

                  then.del()
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
        this.getCart()
        console.log(app.globalData)
    },
    getCart: function(){
        let then=this
        wx.request({
          url: 'http://127.0.0.1:82/cart/my',
          method:"POST",
          data:{
            //uid:app.globalData.uid
            uid:uid_1
            },
          success(data){
              console.log(data);
              then.setData({
                cart:data.data,
                allchecked:0
              })
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
      this.getCart()   
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
    del: function(){
        let then =this;
                  let list=[];
                  list[0]=app.globalData.uid
                  let i=1;
                  for(let j=0;j<then.data.cart.length;j++){
                      if(then.data.cart[j].checked==1){
                        list[i]=then.data.cart[j].goods_id
                        i++
                      }
                  }
                  wx.request({
                    url: 'http://127.0.0.1:82/cart/del',
                    method:"POST",
                    data:list,
                    success(data){
                        console.log(data)
                    }
                  })
                  then.getCart()
                  
                  then.setData({
                      totalPrice:0
                  })
          
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
    


})