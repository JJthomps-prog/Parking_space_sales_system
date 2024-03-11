package com.ts.parking.controller;

import com.ts.parking.dao.UserParkingDao;
import com.ts.parking.pojo.RenChou;
import com.ts.parking.pojo.ShoppingCart;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Author: tang
 * @Date: 2021/10/23/15:03
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("/up")
public class UserParkingController {

    @Autowired
    UserParkingDao userParkingDao;
    @Transactional
    @PostMapping("/user")
    public String user(@RequestBody Map<String,String> map){
        String uid = map.get("uid");
        String open = map.get("open");
        String plid = map.get("plid");
        if(open.equals("0")){//认筹
            List<RenChou> renchou = userParkingDao.renchou(uid);
            System.out.println("renchou"+renchou);
            if(renchou.size()>0){
                return "只能认筹一个车位";
            }else {
                int addrenchou = userParkingDao.Addrenchou(plid, uid);
                if(addrenchou>0){
                    return "认筹成功";
                }else{
                    return "认筹失败";
                }
            }
        }else{//加入购物车
            ShoppingCart shoppingCart = userParkingDao.querySCOne(plid, uid);
            if(shoppingCart!=null){
                return "你的购物车已存在该车位";
            }else{
                int i = userParkingDao.AddSC(plid, uid);
                if(i>0){
                    return "加入购物车成功";
                }else{
                    return "加入购物车失败";
                }
            }
        }
    }
}
