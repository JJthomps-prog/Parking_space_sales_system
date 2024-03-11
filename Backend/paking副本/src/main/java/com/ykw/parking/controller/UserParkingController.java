package com.ykw.parking.controller;

import com.ykw.parking.mapper.RealNameMapper;
import com.ykw.parking.pojo.*;
import com.ykw.parking.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserParkingController {
    @Autowired
    UserParkingService userParkingService;

    @Autowired
    RealNameService realNameService;
    @Autowired
    UserOrderService userOrderService;
    @Autowired
    AdminUserService adminUserService;
    @Autowired
    AdminParkingLotService adminParkingLotService;
    @Autowired
    ParkingManageService parkingManageService;
    @RequestMapping("/userParking")
    public  String getUserParking(HttpSession session){
        List<UserParking> userParkings = userParkingService.QueryAllUserParking();
        session.setAttribute("AlluserParkings",userParkings);
        int UserParkingIndex = userParkingService.QueryAllUserParkingIndex();
        session.setAttribute("UserParkingIndex",UserParkingIndex);
        return "business-list";
    }

    @RequestMapping("/business-add")
    public  String getBusinessAdd(HttpSession session){
        List<String> strings = userParkingService.QueryParkingByCity();
        session.setAttribute("address",strings);
        return "business-add";
    }

    /**
     *
     * @param uid
     * @param number
     * @param city
     * @param address
     * @return 添加车位用户信息
     */
    @PostMapping("/admin/addUserParking")
    @ResponseBody
    public  String addUserParking(String uid,String number,String city,String address){
        int i1 = realNameService.queryRealUserById(uid);
        User user = adminUserService.QueryOne(uid);
        int i2 = userParkingService.QueryBeUserParking(uid, number, city, address);
        UserOrder parkinglotNumber = userOrderService.QueryOneByNumber(number);
        if(user==null){
            return "用户ID不存在！";
        }else if (i1==0){
            return "用户ID未实名！";
        }else if(i2>0){
            return "添加的信息以存在！";
        }else if(parkinglotNumber!=null){
            return "该车位订单已存在，请重新选择车位！";
        }
        Parking parking = parkingManageService.queryParkingByAddress(address);
        RealUser realUser = realNameService.queryRealUser(uid);
        UserParking userParking=new UserParking(uid,parking.getId(),0,city,address,number,
                realUser.getName(), realUser.getId_number(),user.getPhone_number());
        System.out.println(userParking);
        int i = userParkingService.addUserParking(userParking);
        if(i>0){
            int pid=parking.getId();
            adminParkingLotService.updateSale(number,String.valueOf(pid));
            return null;
        }else{
            return "添加失败！";
        }

    }

    @RequestMapping(value = "/admin/delUserParking",method = RequestMethod.POST)
    @ResponseBody
    public String delUserParking(String uid,String pid, String number){
        int i1 = userParkingService.delUserParking(uid.trim(), Integer.valueOf(pid), number.trim());
        if(i1>0){
            int i2 = adminParkingLotService.updateSaleAndZero(number, pid.trim());
            if(i2>0){
                return "1";
            }else{
                return "删除失败！";
            }
        }else{
            return "删除失败!";
        }


    }
}
