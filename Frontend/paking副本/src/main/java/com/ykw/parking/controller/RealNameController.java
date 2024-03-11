package com.ykw.parking.controller;

import com.ykw.parking.pojo.RealUser;
import com.ykw.parking.pojo.User;
import com.ykw.parking.service.AdminUserService;
import com.ykw.parking.service.RealNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class RealNameController {
    @Autowired
    RealNameService realNameService;

    @Autowired
    AdminUserService adminUserService;
    /**
     * @return 返回member-realname.html页面
     */
    @RequestMapping("/member-realname")
    public String memberrealname( HttpSession session){
        List<RealUser> realUsers = realNameService.queryAllRealUser();
        int index = realNameService.queryAllRealUserIndex();
        session.setAttribute("AllrealUsers",realUsers);
        session.setAttribute("index",index);
        session.setAttribute("searchId","");
        return "member-realname";
    }

    /**
     * @return 返回member-addreal.html页面
     */
    @RequestMapping("/member-addreal")
    public String memberaddreal( HttpSession session){
        return "member-addreal";
    }

    /**
     *
     * @param id
     * @param session
     * @return 删除实名用户
     */
    @RequestMapping(value = "/admin/delRealname",method = RequestMethod.POST)
    @ResponseBody
    public String delRealname(String id,HttpSession session){
        int i = realNameService.delRealNameById(id.trim());
        if(i>0){
            int index=(Integer) session.getAttribute("index");
            session.setAttribute("index",index-1);
            adminUserService.UpdateUserByState(0,id.trim());
            return "1";
        }else{
            return null;
        }
    }

    /**
     *
     * @param searchId 搜索的id
     * @param session
     * @return searchId 搜索实名用户功能
     */
    @RequestMapping(value = "/searchRealUser",method = RequestMethod.POST)
    public String searchRealUser(String searchId,HttpSession session){
        if(searchId.equals("")){
            return "redirect:/member-realname";
        }
        List<RealUser> realUsers = realNameService.searchRealUser(searchId);
        int index=(int) realNameService.searchRealUserIndex(searchId);
        session.setAttribute("AllrealUsers",realUsers);
        session.setAttribute("index",index);
        session.setAttribute("searchId",searchId);
        return "member-realname";
    }


    /**
     *
     * @param id 用户id
     * @param rname 真实姓名
     * @param number 身份证号
     * @param session
     * @return 添加实名用户
     */
    @RequestMapping(value = "/admin/addRealname",method = RequestMethod.POST)
    @ResponseBody
    public String addRealname(String id,String rname,String number,HttpSession session){
        User user = adminUserService.QueryOne(id);
        if (user==null){
            return "用户ID不存在！";
        }
        if (realNameService.queryRealUserById(id)>0){
            return "用户ID已实名！";
        }

        if(realNameService.queryRealUserByNum(number)>0){
            return "身份证号已存在！";
        }
        RealUser realUser=new RealUser(id,user.getName(),user.getPhone_number(),user.getEmail(),rname,number);
        System.out.println(realUser);
        int i = realNameService.AddRealUser(realUser);
        if(i>0){
            adminUserService.UpdateUserByState(1,id);
            return null;
        }else {
            return "添加失败！";
        }
    }
}
