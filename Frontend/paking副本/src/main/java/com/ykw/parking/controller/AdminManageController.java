package com.ykw.parking.controller;

import com.ykw.parking.pojo.Admin;
import com.ykw.parking.service.AdminService;
import com.ykw.parking.uitl.GetUUID;
import com.ykw.parking.uitl.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @Author: tang
 * @Date: 2021/09/02/20:30
 * @Description: 管理员controller
 */
@Controller
public class AdminManageController {
    @Autowired
    AdminService adminService;

    @RequestMapping("/getAdmin")
    public String getAdmin(HttpSession session){
        List<Admin> admins = adminService.queryAdminAll();
        int i = adminService.queryAllAdmin();
        session.setAttribute("admins",admins);
        session.setAttribute("AdminIndex",i);
        return "admin-list";
    }

    @RequestMapping("/admin-add")
    public String getadminAdd(HttpSession session){
        return "admin-add";
    }

    @RequestMapping(value = "/admin/AddAdmin",method = RequestMethod.POST)
    @ResponseBody
    public String addAdmin(@RequestParam("name") String name,@RequestParam("password") String password,@RequestParam("role") String role, HttpSession session){
        Admin admin = adminService.queryAdminByName(name);
        if(admin!=null){
            return "登录名已存在！";
        }
        Admin adminBean=new Admin(GetUUID.getUUID(),name, Md5.code(password),Integer.valueOf(role));
        int i = adminService.addAdmin(adminBean);
        if(i>0){
            return null;
        }else {
            return "添加失败！";
        }
    }

    @RequestMapping("/admin/delAdmin")
    @ResponseBody
    public String delAdmin(@RequestParam("id") String id){
        int i = adminService.delAdmin(id.trim());
        if(i>0){
            return "1";
        }else{
            return null;
        }
    }

    @RequestMapping("/admin-edit")
    public String adminedit(){
        return "admin-edit";
    }


    @RequestMapping("/admin/queryCurrentAdmin")
    @ResponseBody
    public void queryCurrentAdmin(String id,HttpSession session){
        Admin admin = adminService.queryAdminById(id.trim());
        session.setAttribute("UpdateAdmin",admin);
    }

    @PostMapping("/admin/updateAdmin")
    @ResponseBody
    public String updateAdmin(Admin admin,HttpSession session){
        System.out.println(admin);
        if(admin.getRole()==null){
            Admin updateAdmin =(Admin) session.getAttribute("UpdateAdmin");
            admin.setRole(updateAdmin.getRole());
        }
        int i = adminService.updateAdmin(admin);
        if(i>0){
            return null;
        }else{
           return  "编辑失败！";
        }

    }
}
