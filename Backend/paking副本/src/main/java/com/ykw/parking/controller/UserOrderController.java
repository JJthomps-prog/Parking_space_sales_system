package com.ykw.parking.controller;

import com.ykw.parking.pojo.*;
import com.ykw.parking.service.*;
import com.ykw.parking.uitl.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class UserOrderController {
    @Autowired
    UserParkingService userParkingService;
    @Autowired
    UserOrderService userOrderService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    RealNameService realNameService;

    @Autowired
    AdminUserService adminUserService;

    @Autowired
    ParkingManageService parkingManageService;

    @Autowired
    AdminParkingLotService adminParkingLotService;
    @RequestMapping("/orderList")
    public String orderList(HttpSession session){
        List<UserOrder> userOrders = userOrderService.QueryAllOrder();
        int orderIndex = userOrderService.QueryAllOrderIndex();
        session.setAttribute("order",userOrders);
        session.setAttribute("orderIndex",orderIndex);
        session.setAttribute("orderDate","");
        return "order-list";
    }

    @RequestMapping("/order-add")
    public String orderAdd(HttpSession session){
        List<String> strings = userParkingService.QueryParkingByCity();
        session.setAttribute("address",strings);
        return "order-add";
    }

    @RequestMapping("/admin/addOrder")
    @ResponseBody
    public  String addOrder(@RequestParam("uid") String uid, String number, String city, String address, int state){
        int i1 = realNameService.queryRealUserById(uid);
        User user = adminUserService.QueryOne(uid);
        int i2 = userParkingService.QueryBeUserParking(uid, number, city, address);
        UserOrder parkinglotNumber = userOrderService.QueryOneByNumber(number);
        if(user==null){
            return "用户ID不存在！";
        }else if (i1==0){
            return "用户ID未实名！";
        }else if(i2>0){
            return "添加的信息已存在！";
        }else if(parkinglotNumber!=null){
            return "该车位订单已存在，请重新选择车位！";
        }
        Parking parking = parkingManageService.queryParkingByAddress(address);
        ParkingLot parkingLot = adminParkingLotService.queryParkingLotOne(number, String.valueOf(parking.getId()));
        if(state==1){
            UserOrder userOrder=new UserOrder(new SnowFlake(2,3).nextId(),
                    uid,new Date(System.currentTimeMillis()),parking.getId(),number,city,address,
                    state,parkingLot.getPrice(),parkingLot.getId());
            RealUser realUser = realNameService.queryRealUser(uid);
            UserParking userParking=new UserParking(uid,parking.getId(),0,city,address,number,
                    realUser.getName(),realUser.getId_number(),realUser.getPhone_number());
           userParkingService.addUserParking(userParking);
            adminParkingLotService.updateSale(number,String.valueOf(parking.getId()));
            int i = userOrderService.addOrder(userOrder);
            if(i>0){
                SimpleDateFormat sdf=new SimpleDateFormat("MM-dd");
                java.util.Date date=new java.util.Date();
                String str=sdf.format(date);
                stringRedisTemplate.opsForValue().increment(str);
                return null;
            }else{
                return "添加失败！";
            }
        }else{
            UserOrder userOrder=new UserOrder(new SnowFlake(2,3).nextId(),
                    uid,new Date(System.currentTimeMillis()),parking.getId(),number,city,address,
                    state,parkingLot.getPrice(),parkingLot.getId());
            int i = userOrderService.addOrder(userOrder);
            if(i>0){
                return null;
            }else{
                return "添加失败！";
            }
        }
    }


    @RequestMapping(value = "/admin/delOrder" ,method = RequestMethod.POST)
    @ResponseBody
    public String delOrder(@RequestParam("oid") String oid){
        int i = userOrderService.delOrderByOid(oid.trim());
        if(i>0){
            return "1";
        }else{
            return null;
        }
    }

    @GetMapping("/admin/searchOrder")
    public String searchOrder(@RequestParam("date") String date,@RequestParam("state") String state,HttpSession session){
        session.setAttribute("orderDate",date);
        if(date!="") {
            if(state!=""){
                List<UserOrder> userOrders = userOrderService.QueryOrderByStateAndDate(state, date);
                session.setAttribute("order",userOrders);
                return "redirect:/searchOrderList";
            }else{
                List<UserOrder> userOrders = userOrderService.QueryOrderByDate(date);
                session.setAttribute("order",userOrders);
                return "redirect:/searchOrderList";
            }
        }else{
            if(state!=""){
                List<UserOrder> userOrders = userOrderService.QueryOrderByState(state);
                session.setAttribute("order",userOrders);
                return "redirect:/searchOrderList";
            }else{
                return "redirect:/orderList";
            }
        }

    }

    @RequestMapping("/searchOrderList")
    public String searchOrderList(HttpSession session){
        return "order-list";
    }
}
