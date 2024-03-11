package com.ts.parking.controller;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.ts.parking.dao.*;
import com.ts.parking.pojo.*;
import com.ts.parking.uitl.SnowFlake;
import com.ts.parking.vo.ShoppingCartVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: tang
 * @Date: 2021/10/25/15:42
 * @Description:
 */
@RestController
@RequestMapping("/cart")
public class ShoppingCartController {

    @Autowired
    ShoppingCartDao shoppingCartDao;

    @Autowired
    ParkingLotDao parkingLotDao;
    @Autowired
    ParkingDao parkingDao;

    @Transactional
    @PostMapping("/my")
    public List<ShoppingCartVo> myCart(@RequestBody Map<String,String> map){
        String uid=map.get("uid");
        System.out.println(uid);
        List<ShoppingCartVo> shoppingCartVos=new ArrayList<>();
        List<ShoppingCart> myCart = shoppingCartDao.getMyCart(uid);
        myCart.forEach(item->{
            ShoppingCartVo shoppingCartVo = new ShoppingCartVo();
            ParkingLot parkingLot = parkingLotDao.queryById(Integer.valueOf(item.getParkingid()));
            shoppingCartVo.setPrice(parkingLot.getPrice());
            shoppingCartVo.setNumber(parkingLot.getNumber());
            Parking parking = parkingDao.queryByid(Integer.valueOf(parkingLot.getPid()));
            shoppingCartVo.setAddress(parking.getAddress());
            shoppingCartVo.setGoods_id(Integer.valueOf(item.getParkingid()));
            shoppingCartVos.add(shoppingCartVo);
        });
        return shoppingCartVos;
    }

    @PostMapping("/del")
    public Integer del(@RequestBody List<String> list){
        if(list.get(1)==null){
            return -1;
        }else{
            for(int i=1;i<list.size();i++){
                shoppingCartDao.del(list.get(0),list.get(i));
            }
        }
        return 0;
    }

@Autowired
    UserOrderDao userOrderDao;

    @Transactional
    @PostMapping("/order")
    public Integer order(@RequestBody List<String> list){
        Date date = new Date(System.currentTimeMillis());
        Bank bank = shoppingCartDao.queryOne(list.get(0));
        System.out.println(list.toString());
        if(list.get(2)==null||bank==null||(bank.getMoney()<Double.valueOf(list.get(1)))){
            return -1;
        }else{
            shoppingCartDao.updBank(list.get(0),bank.getMoney()-Double.valueOf(list.get(1)));
            for(int i=2;i<list.size();i++){
                parkingLotDao.updState(1,Integer.valueOf(list.get(i)));
                ParkingLot parkingLot = parkingLotDao.queryById(Integer.valueOf(list.get(i)));
                Parking parking = parkingDao.queryByid(Integer.valueOf(parkingLot.getPid()));
                UserOrder userOrder = new UserOrder();
                userOrder.setOid(new SnowFlake(2,3).nextId());
                userOrder.setUid(list.get(0));
                userOrder.setDate(date);
                userOrder.setPid(parking.getId());
                userOrder.setNumber(parkingLot.getNumber());
                userOrder.setCity(parking.getCity());
                userOrder.setAddress(parking.getAddress());
                userOrder.setState(1);
                userOrder.setPrice(parkingLot.getPrice());
                userOrder.setPlid(parkingLot.getId());
                userOrderDao.addOrder(userOrder);
            }
        }
        return 0;
    }
}
