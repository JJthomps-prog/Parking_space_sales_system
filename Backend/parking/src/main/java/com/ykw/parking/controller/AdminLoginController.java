package com.ykw.parking.controller;

import com.ykw.parking.mapper.OwnerMapper;
import com.ykw.parking.pojo.Admin;
import com.ykw.parking.service.AdminService;
import com.ykw.parking.service.AdminUserService;
import com.ykw.parking.uitl.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class AdminLoginController {

    @Autowired()
    AdminService adminService;

    @Autowired
    OwnerMapper ownerMapper;
    @Autowired
    AdminUserService adminUserService;
    @GetMapping(value = {"/","/login"})
    public String loginPage(HttpSession session){
        return "login";
    }

    @PostMapping("/login")
    public String main(String admin_name,String admin_password, HttpSession session,Model model){
        Admin admin1=adminService.queryAdminByNameAndPwd(admin_name, Md5.code(admin_password));
        if(admin1!=null) {
            session.setAttribute("admin",admin1);
            return "redirect:/index.html";
        }else {
            return "login";
        }
    }

    /**防止表达重复提交最佳方法重定向*/

    @GetMapping("/index.html")
    public  String mainPage(HttpSession session, Model model){
        Object admin = session.getAttribute("admin");
        if(admin==null){
            return "login";
        }else {
            int i = adminUserService.QueryAllUserIndex();
            int AdminIndex=adminService.queryAllAdmin();
            model.addAttribute("UserIndex",i);
            model.addAttribute("AdminIndex",AdminIndex);
            model.addAttribute("parkingNum",ownerMapper.queryAllParkingNumber());
            model.addAttribute("userParkingNum",ownerMapper.queryAllUserP());

            return "index";
        }
    }

    @PostMapping("/nameAndPwd")
    @ResponseBody
    public String nameAndPwd(String admin_name,String admin_password, HttpSession session,Model model){
        Admin admin1=adminService.queryAdminByNameAndPwd(admin_name, Md5.code(admin_password));
        System.out.println(admin_name);
        if(admin1!=null) {
            return null;
        }else {
            return "账号或密码错误！";
        }
    }
}
