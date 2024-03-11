package com.ts.parking.controller;

import com.ts.parking.dao.ParkingLotDao;
import com.ts.parking.dao.ShoppingCartDao;
import com.ts.parking.dao.UserOrderDao;
import com.ts.parking.pojo.Bank;
import com.ts.parking.pojo.UserOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: tang
 * @Date: 2021/10/31/15:07
 * @Description:
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    UserOrderDao userOrderDao;

    @Autowired
    ParkingLotDao parkingLotDao;
    @Autowired
    ShoppingCartDao shoppingCartDao;
    @PostMapping("/getall")
    public HashMap<String, List<UserOrder>> getall(@RequestBody Map<String,String> map){
        List<UserOrder> uid = userOrderDao.getAllOrder(map.get("uid"), "1");
        List<UserOrder> uid1 = userOrderDao.getAllOrder(map.get("uid"), "0");
        HashMap<String, List<UserOrder>> stringListHashMap = new HashMap<>();
        stringListHashMap.put("Tobepaid",uid1);
        stringListHashMap.put("payment",uid);
        return stringListHashMap;
    }

    @GetMapping("/delete")
    public void delete(@RequestParam("oid") String oid){
        userOrderDao.delete(oid,"-1");
    }

    @GetMapping("/zf")
    public void zf(@RequestParam("oid") String oid,@RequestParam("price") Double price,@RequestParam("uid") String uid){
        UserOrder one = userOrderDao.getOne(oid);
        Bank bank = shoppingCartDao.queryOne(uid);
        if(bank.getMoney()<one.getPrice()){

        }else{
            userOrderDao.delete(oid,"1");
            parkingLotDao.updState(one.getPlid(),1);
        }

    }
}
