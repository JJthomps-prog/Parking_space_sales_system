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

/**
 * @Author: tang
 * @Date: 2021/10/18/15:18
 * @Description:
 */
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
        String sql="select * from user where name="+"\""+user.getEmail()+"\"";
        try {
            List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
            if (maps.size()==0){
                return "not user";
            }
            if (maps.size()==1){
                String o =(String) maps.get(0).get(user.getEmail());
                if(o.equals(Md5.code(user.getPassword()))==true){
                    return "success";
                }else{
                    return "password error";
                }
            }else{
                return "known error";
            }
        }catch (Exception e){
            return "known error";
        }
    }



}
