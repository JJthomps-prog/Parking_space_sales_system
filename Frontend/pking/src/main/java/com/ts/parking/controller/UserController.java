package com.ts.parking.controller;

import com.ts.parking.dao.UserDao;
import com.ts.parking.pojo.RealUser;
import com.ts.parking.pojo.User;
import com.ts.parking.pojo.UserOwner;
import com.ts.parking.uitl.Md5;
import com.ts.parking.vo.UserIdVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserDao userDao;
    @PostMapping("/login")
    public HashMap<String, Object> UserLogin(@RequestBody Map<String,String> map){
        String password = map.get("password");
        String userEmail = map.get("userEmail");
        HashMap<String, Object> hashMap = new HashMap<>();
        User user = userDao.queryUserLogin(userEmail, Md5.code(password));
        if(user==null){
            hashMap.put("code",400);
            return hashMap;
        }else{
            RealUser realUser = userDao.queryReaUser(user.getId());
            UserOwner userOwner = userDao.queryUserOwner(user.getId());
            if(realUser!=null&&userOwner!=null) {
                hashMap.put("code", 200);
                hashMap.put("userInfo", user);
                return hashMap;
            }else{
                hashMap.put("code", 500);
                return hashMap;
            }
        }
    }

    @PostMapping("/id")
    public UserIdVo id(@RequestBody  Map<String,String> map){

        RealUser realUser = userDao.queryUserR(map.get("uid"));
        System.out.println(realUser);
        UserIdVo userIdVo = new UserIdVo();
        userIdVo.setId(realUser.getId());
        userIdVo.setName(realUser.getRname());
        userIdVo.setPhone(realUser.getEmail());
        return userIdVo;
    }
}
