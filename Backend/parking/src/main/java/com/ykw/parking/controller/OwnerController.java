package com.ykw.parking.controller;

import com.ykw.parking.mapper.OwnerMapper;
import com.ykw.parking.pojo.Parking;
import com.ykw.parking.pojo.RealUser;
import com.ykw.parking.pojo.UserOwner;
import com.ykw.parking.service.ParkingManageService;
import com.ykw.parking.service.RealNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class OwnerController {
    @Autowired
    ParkingManageService parkingManageService;
    @Autowired
    OwnerMapper ownerMapper;
    @Autowired
    RealNameService realNameService;
    @RequestMapping("/owner-list")
    public String ownerList(HttpSession session){
        session.setAttribute("owner",ownerMapper.queryAllOwner());
        session.setAttribute("index",ownerMapper.queryAllOwnerIndex());
        return "owner-list";
    }

    @RequestMapping("/owner-add")
    public String ownerAddPage(){
        return "owner-add";
    }

    @RequestMapping("/owner/updateOwner")
    @ResponseBody
    public void updateOwnerPage(String uid,HttpSession session){
        UserOwner userOwner = ownerMapper.queryOwnerByUid(uid.trim());
        session.setAttribute("ownerBean",userOwner);
    }

    @PostMapping("/owner/updateOwner1")
    @ResponseBody
    public void updateOwner(String uid,String state,String city,String address,HttpSession session){
        RealUser realUser = realNameService.queryRealUser(uid);
        int i = ownerMapper.updateOwnerByState(uid, Integer.valueOf(state));
        if(i>0&&state.equals("1")){
            parkingManageService.AddParking(new Parking(null,city,address,realUser.getPhone_number(),realUser.getRname(),0));
        }
    }
}
