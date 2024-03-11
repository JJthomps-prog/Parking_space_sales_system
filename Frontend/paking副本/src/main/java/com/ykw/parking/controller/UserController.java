package com.ykw.parking.controller;

import com.ykw.parking.pojo.User;
import com.ykw.parking.service.AdminUserService;
import com.ykw.parking.service.RealNameService;
import com.ykw.parking.service.SearchUserService;
import com.ykw.parking.uitl.GetUUID;
import com.ykw.parking.uitl.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    AdminUserService adminUserService;
    @Autowired
    RealNameService realNameService;
    @Autowired
    SearchUserService searchUserService;

    @Autowired
    public RedisTemplate<String, Object> redisTemplate;
    @RequestMapping("/member-add")
    public String GetMember(){
        return "member-add";
    }

    @RequestMapping("/member-edit")
    public String GetMemberEdit(){
        return "member-edit";
    }

    @RequestMapping("/member-list")
    public String GetMemberList(HttpSession session){
        String searchName=(String) session.getAttribute("searchName");
        String searchPhone=(String) session.getAttribute("searchPhone");
        String searchEmail=(String) session.getAttribute("searchEmail");
        return "redirect:/searchUser?searchName="+searchName+"&searchPhone="+searchPhone+"&searchEmail="+searchEmail;
    }

    /**
     *
     * @param session
     * @return 用户列表
     */
    @RequestMapping("/getUser")
    public String getUser( HttpSession session){
        int i = adminUserService.QueryAllUserIndex();
        List<User> users = adminUserService.QueryAllUser();
        session.setAttribute("AdminUser",users);
        session.setAttribute("UserIndex",i);
        session.setAttribute("searchName","");
        session.setAttribute("searchPhone","");
        session.setAttribute("searchEmail","");
        return "member-list";
    }


    /**
     *
     * @param value
     * @param session
     * @return 删除用户
     */
    @RequestMapping("/admin/delete")
    @ResponseBody
    public String AdminDeleteUser(String value,HttpSession session){
        String trim = value.trim();
        int i = adminUserService.DeleteUser(trim);
        if(i==1){
            realNameService.delRealNameById(trim);
            Integer index =(Integer) session.getAttribute("UserIndex");
            session.setAttribute("UserIndex",index-1);
            return "1";
        }else {
            return null;
        }

    }

    /**
     *
     * @param user
     * @param session
     * @return 添加用户
     */
    @PostMapping("/admin/adduser")
    @ResponseBody
    public String AddUser(User user,HttpSession session ){
        user.setId(GetUUID.getUUID());
        int i1 = adminUserService.QueryAllUserByName(user.getName());
        int i2 = adminUserService.QueryAllUserByPhone(user.getPhone_number());
        int i3 = adminUserService.QueryAllUserByEmail(user.getEmail());
        if(i1!=0){
            return "用户名已存在！";
        }
        if (i2!=0){
            return "手机号已存在！";
        }

        if(i3!=0){
            return "邮箱已存在！";
        }

        user.setPassword(Md5.code(user.getPassword()));
        int i = adminUserService.AddUser(user);
        if(i>0) {
            SimpleDateFormat sdf=new SimpleDateFormat("MM-dd");
            java.util.Date date=new java.util.Date();
            String str=sdf.format(date);
            System.out.println(str);
            redisTemplate.opsForHash().increment("user",str,1);
            return null;
        }else{
            return "添加失败";
        }
    }

    /**
     *
     * @param user
     * @param session
     * @return 编辑用户
     */
    @PostMapping("/admin/updateUser")
    @ResponseBody
    public String updateUser(User user,HttpSession session ){
        User UpdateUserBean=(User) session.getAttribute("UpdateUserBean");
        int i1 = adminUserService.QueryAllUserByName(user.getName());
        int i2 = adminUserService.QueryAllUserByPhone(user.getPhone_number());
        if(!user.getPhone_number().equals(UpdateUserBean.getPhone_number())&& !user.getName().equals(UpdateUserBean.getName())){
            if(i1!=0){
                return "用户名已存在！";
            }
            if (i2!=0){
                return "手机号已存在！";
            }
        }
        if(!user.getPhone_number().equals(UpdateUserBean.getPhone_number())&&user.getName().equals(UpdateUserBean.getName())){
            if(i2!=0){
                return "手机号已存在！";
            }
        }
        if(!user.getName().equals(UpdateUserBean.getName())&&user.getPhone_number().equals(UpdateUserBean.getPhone_number())){
            if (i1!=0){
                return "用户名已存在！";
            }
        }
        int i = adminUserService.UpdateUser(user);
        if(i!=0){
            realNameService.updateEmailAndPhone(user.getEmail(),user.getPhone_number(),user.getId());
            return null;
        }else {
            return "编辑失败！";
        }
    }

    /**
     * @param searchName
     * @param searchPhone
     * @param searchEmail
     * @param session
     * @return 用户搜索功能
     */
    @GetMapping("/searchUser")
    public String searchUser(String searchName,String searchPhone,String searchEmail,HttpSession session){
        if(searchName==""&&searchEmail==""&&searchPhone==""){
            return "redirect:/getUser";
        }
        if(searchName!=""&&searchEmail==""&&searchPhone==""){
            List<User> users = searchUserService.QueryUserByName(searchName);
            int i = searchUserService.QueryUserByNameInt(searchName);
            session.setAttribute("UserIndex",i);
            session.setAttribute("AdminUser",users);
            session.setAttribute("searchName",searchName);
            session.setAttribute("searchPhone","");
            session.setAttribute("searchEmail","");
            return "member-list";
        }
        if(searchName==""&&searchEmail!=""&&searchPhone==""){
            List<User> users = searchUserService.QueryUserByEmail(searchEmail);
            int i = searchUserService.QueryUserByEmailInt(searchEmail);
            session.setAttribute("UserIndex",i);
            session.setAttribute("AdminUser",users);
            session.setAttribute("searchName","");
            session.setAttribute("searchPhone","");
            session.setAttribute("searchEmail",searchEmail);
            return "member-list";
        }
        if(searchName==""&&searchEmail==""&&searchPhone!=""){
            List<User> users = searchUserService.QueryUserByPhone(searchPhone);
            int i = searchUserService.QueryUserByPhoneInt(searchPhone);
            session.setAttribute("UserIndex",i);
            session.setAttribute("AdminUser",users);
            session.setAttribute("searchName","");
            session.setAttribute("searchPhone",searchPhone);
            session.setAttribute("searchEmail","");
            return "member-list";
        }
        if(searchName!=""&&searchEmail!=""&&searchPhone==""){
            List<User> users = searchUserService.QueryUserByNameAndEmail(searchName, searchEmail);
            int i = searchUserService.QueryUserByNameAndEmailInt(searchName, searchEmail);
            session.setAttribute("UserIndex",i);
            session.setAttribute("AdminUser",users);
            session.setAttribute("searchName",searchName);
            session.setAttribute("searchPhone","");
            session.setAttribute("searchEmail",searchEmail);
            return "member-list";
        }
        if(searchName==""&&searchEmail!=""&&searchPhone!=""){
            List<User> users = searchUserService.QueryUserByPhoneAndEmail(searchPhone, searchEmail);
            int i = searchUserService.QueryUserByPhoneAndEmailInt(searchPhone, searchEmail);
            session.setAttribute("UserIndex",i);
            session.setAttribute("AdminUser",users);
            session.setAttribute("searchName","");
            session.setAttribute("searchPhone",searchPhone);
            session.setAttribute("searchEmail",searchEmail);
            return "member-list";
        }
        if(searchName!=""&&searchEmail==""&&searchPhone!=""){
            List<User> users = searchUserService.QueryUserByNameAndPhone(searchName, searchPhone);
            int i = searchUserService.QueryUserByNameAndPhoneInt(searchName, searchEmail);
            session.setAttribute("UserIndex",i);
            session.setAttribute("AdminUser",users);
            session.setAttribute("searchName",searchName);
            session.setAttribute("searchPhone",searchPhone);
            session.setAttribute("searchEmail","");
            return "member-list";
        }
        if(searchName!=""&&searchEmail!=""&&searchPhone!=""){
            List<User> users = searchUserService.QueryUserByNameAndPhoneAndEmail(searchName, searchPhone, searchEmail);
            int i = searchUserService.QueryUserByNameAndPhoneAndEmailInt(searchName, searchPhone, searchEmail);
            session.setAttribute("UserIndex",i);
            session.setAttribute("AdminUser",users);
            session.setAttribute("searchName",searchName);
            session.setAttribute("searchPhone",searchPhone);
            session.setAttribute("searchEmail",searchEmail);
            return "member-list";
        }
        return "member-list";
    }
}
