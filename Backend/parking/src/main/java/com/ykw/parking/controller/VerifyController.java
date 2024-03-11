package com.ykw.parking.controller;

import com.ykw.parking.pojo.Parking;
import com.ykw.parking.pojo.User;
import com.ykw.parking.service.AdminParkingLotService;
import com.ykw.parking.service.AdminUserService;
import com.ykw.parking.service.ParkingManageService;
import com.ykw.parking.service.RealNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 核实信息专用
 */
@Controller
public class VerifyController {
    @Autowired
    AdminUserService adminUserService;
    @Autowired
    ParkingManageService parkingManageService;
    @Autowired
    AdminParkingLotService adminParkingLotService;
    @Autowired
    RealNameService realNameService;
    @RequestMapping("/admin/VerifyUserName")//核实用户昵称
    @ResponseBody
    public String VerifyUserName(String name){
        int i = adminUserService.QueryAllUserByName(name);
        if(i!=0){
            return null;
        }else{
            return "1";
        }
    }

    @RequestMapping("/admin/VerifyUserPhone")//核实用户手机号
    @ResponseBody
    public  String VerifyUserPhone(String phone_number){
        int i = adminUserService.QueryAllUserByPhone(phone_number);
        if(i!=0){
            return null;
        }else{
            return "1";
        }
    }
/*
* updateUserBean  编辑数据不用重新输入该用户的信息
*/
    @RequestMapping("/admin/updateUserBean")
    @ResponseBody
    public void updateUserBean(String id, HttpSession session){
        String trim = id.trim();
        System.out.println("更新的id="+trim);
        User user = adminUserService.QueryOne(trim);
        session.setAttribute("UpdateUserBean",user);
    }

    @RequestMapping("/admin/verifyAddress")
    @ResponseBody
    public String verifyAddress(String address){
        Parking parking = parkingManageService.queryParkingByAddress(address);
        if(parking!=null){
            return "地址已存在！";
        }else{
            return null;
        }
    }

    @RequestMapping("/admin/verifyNumber")
    @ResponseBody
    public String verifyNumber(String number,String pid){
        int i = adminParkingLotService.queryParkingLotByNumAndPid(number, pid);
        if(i>0){
            return "编号已存在！";
        }else{
            return null;
        }
    }

    @RequestMapping(value = "/admin/VerifyRealId",method = RequestMethod.POST)
    @ResponseBody
    public String verifyNumber(String id){
        int i = realNameService.queryRealUserById(id);
        User user = adminUserService.QueryOne(id);
        if(user==null){
            return "用户ID不存在！";
        }else if (i>0){
            return "用户ID已实名！";
        }else {
            return null;
        }
    }

    @RequestMapping(value = "/admin/VerifyRealIdNumber",method = RequestMethod.POST)
    @ResponseBody
    public String VerifyRealIdNumber(String number){
        int i = realNameService.queryRealUserByNum(number);
        if(i>0){
            return "身份证号已存在！";
        }else{
            return null;
        }
    }

    @RequestMapping(value = "/admin/obtainNumber",method = RequestMethod.POST)
    @ResponseBody
    public List<String> obtainNumber(String address){
        int id = parkingManageService.getParkingIdByAddress(address);
        List<String> list = adminParkingLotService.getPLpidByNumber(String.valueOf(id));
        return list;
    }

    @RequestMapping(value = "/admin/VerifyRealUId",method = RequestMethod.POST)
    @ResponseBody
    public String VerifyRealUId(String id){
        int i = realNameService.queryRealUserById(id);
        User user = adminUserService.QueryOne(id);
        if(user==null){
            return "用户ID不存在！";
        }else if (i==0){
            return "用户ID未实名！";
        }else {
            return null;
        }
    }
}
