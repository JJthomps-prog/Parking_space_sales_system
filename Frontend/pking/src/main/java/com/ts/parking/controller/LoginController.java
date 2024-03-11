package com.ts.parking.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.ts.parking.pojo.User;
import com.ts.parking.uitl.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sql")
public class LoginController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/")
    public String test(){
        return "hello";
    }
    @PostMapping("/login")
    public String Login(@RequestBody User user){
        System.out.println(user);
        String sql="select * from user where email="+"\""+user.getEmail()+"\"";
        System.out.println(sql);
        try {
            List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
            System.out.println(maps);
            System.out.println(maps.size()==1);
            if (maps.size()==1){
                //return "not user";
                return maps.toString();
            }
            if (maps.size()==1){
                String o =(String) maps.get(0).get(user.getPassword());
                System.out.println(o);
                if(o.equals(Md5.code(user.getPassword()))==true){
                    return "success";
                }else{
                    return "password error";
                }
            }else{
                System.out.println(maps.size());
                return "known error";
            }
        }catch (Exception e){
            return "known error";
        }
    }
}
