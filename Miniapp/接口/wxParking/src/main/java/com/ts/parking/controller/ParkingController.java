package com.ts.parking.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ts.parking.dao.ParkingDao;
import com.ts.parking.dao.ParkingLotDao;
import com.ts.parking.pojo.Parking;
import com.ts.parking.pojo.ParkingLot;
import com.ts.parking.vo.NumberAndExplainVo;
import com.ts.parking.vo.ParkingVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @Author: tang
 * @Date: 2021/10/20/20:23
 * @Description:
 */
@RestController
@RequestMapping("/api")
public class ParkingController {

    @Autowired
    ParkingDao parkingDao;

    @Autowired
    ParkingLotDao parkingLotDao;
    @GetMapping("/getParking")
    public List<ParkingVo> getParking(){
        List<ParkingVo> parkingVos=new ArrayList<>();
        List<Parking> parkingDaos = parkingDao.selectList(new QueryWrapper<>());
        System.out.println("get"+parkingDaos);
        parkingDaos.forEach(item->{
            List<ParkingLot> parkingLots = parkingDao.queryByPid(item.getId(),0);
            if(parkingLots!=null||parkingLots.size()!=0) {
                parkingLots.forEach(mune -> {
                    ParkingVo parkingVo = new ParkingVo();
                    parkingVo.setId(mune.getId());
                    parkingVo.setPid(item.getId());
                    parkingVo.setAddress(item.getAddress());
                    parkingVo.setPrice(mune.getPrice());
                    parkingVo.setOpen(mune.getOpen());
                    parkingVo.setOwner_name(item.getOwner_name());
                    parkingVo.setOwner_phone(item.getOwner_phone());
                    parkingVos.add(parkingVo);
                });
            }
        });
        return parkingVos;
    }

    @GetMapping("/getOneP")
    public NumberAndExplainVo getOneP(Integer id){
        NumberAndExplainVo numberAndExplainVo = new NumberAndExplainVo();
        ParkingLot parkingLots = parkingLotDao.queryById(id);
        if(parkingLots!=null){
            numberAndExplainVo.setNumber(parkingLots.getNumber());
            numberAndExplainVo.setExplain(parkingLots.getExplain());
        }
        return  numberAndExplainVo;
    }

    @PostMapping("/searchs")
    public List<ParkingVo> searchs(@RequestBody Map<String,String> map){
        String s = map.get("open");
        String s1 = map.get("price");
        List<ParkingVo> parkingVos=new ArrayList<>();
        List<Parking> parkingDaos = parkingDao.selectList(new QueryWrapper<>());

        if(s.equals("3")){
            parkingDaos.forEach(item->{
                List<ParkingLot> parkingLots = parkingLotDao.queryByPid(item.getId(),0,0);
                if(parkingLots!=null||parkingLots.size()!=0) {
                    parkingLots.forEach(mune -> {
                        ParkingVo parkingVo = new ParkingVo();
                        parkingVo.setId(mune.getId());
                        parkingVo.setPid(item.getId());
                        parkingVo.setAddress(item.getAddress());
                        parkingVo.setPrice(mune.getPrice());
                        parkingVo.setOpen(mune.getOpen());
                        parkingVo.setOwner_name(item.getOwner_name());
                        parkingVo.setOwner_phone(item.getOwner_phone());
                        parkingVos.add(parkingVo);
                    });
                }
            });
        }else if(s.equals("4")){
            parkingDaos.forEach(item->{
                List<ParkingLot> parkingLots = parkingLotDao.queryByPid(item.getId(),0,1);
                if(parkingLots!=null||parkingLots.size()!=0) {
                    parkingLots.forEach(mune -> {
                        ParkingVo parkingVo = new ParkingVo();
                        parkingVo.setId(mune.getId());
                        parkingVo.setPid(item.getId());
                        parkingVo.setAddress(item.getAddress());
                        parkingVo.setPrice(mune.getPrice());
                        parkingVo.setOpen(mune.getOpen());
                        parkingVo.setOwner_name(item.getOwner_name());
                        parkingVo.setOwner_phone(item.getOwner_phone());
                        parkingVos.add(parkingVo);
                    });
                }
            });
        }else if(s.equals("3")){
            parkingDaos.forEach(item->{
                List<ParkingLot> parkingLots = parkingDao.queryByPid(item.getId(),0);
                if(parkingLots!=null||parkingLots.size()!=0) {
                    parkingLots.forEach(mune -> {
                        ParkingVo parkingVo = new ParkingVo();
                        parkingVo.setId(mune.getId());
                        parkingVo.setPid(item.getId());
                        parkingVo.setAddress(item.getAddress());
                        parkingVo.setPrice(mune.getPrice());
                        parkingVo.setOpen(mune.getOpen());
                        parkingVo.setOwner_name(item.getOwner_name());
                        parkingVo.setOwner_phone(item.getOwner_phone());
                        parkingVos.add(parkingVo);
                    });
                }
            });
        }

        if(s1.equals("b")){//低到高
            Collections.sort(parkingVos, new Comparator<ParkingVo>() {
                @Override
                public int compare(ParkingVo o1, ParkingVo o2) {
                    int i = o1.getPrice().compareTo(o2.getPrice());
                    return i;
                }
            });
        }else if(s1.equals("c")){ //高到低
            Collections.sort(parkingVos, new Comparator<ParkingVo>() {
                @Override
                public int compare(ParkingVo o1, ParkingVo o2) {
                    int i = o2.getPrice().compareTo(o1.getPrice());
                    return i;
                }
            });
        }
        return parkingVos;
    }

    @GetMapping("/getParkingLike")
    public List<ParkingVo> getParkingLike(@RequestParam("like") String like){
        if(like.equals("")||like==null){
            return null;
        }
        List<ParkingVo> parkingVos=new ArrayList<>();
        List<Parking> parkingDaos = parkingDao.queryByNameLike(like);
        System.out.println(parkingDaos);
        parkingDaos.forEach(item->{
            List<ParkingLot> parkingLots = parkingDao.queryByPid(item.getId(),0);
            if(parkingLots!=null||parkingLots.size()!=0) {
                parkingLots.forEach(mune -> {
                    ParkingVo parkingVo = new ParkingVo();
                    parkingVo.setId(mune.getId());
                    parkingVo.setPid(item.getId());
                    parkingVo.setAddress(item.getAddress());
                    parkingVo.setPrice(mune.getPrice());
                    parkingVo.setOpen(mune.getOpen());
                    parkingVo.setOwner_name(item.getOwner_name());
                    parkingVo.setOwner_phone(item.getOwner_phone());
                    parkingVos.add(parkingVo);
                });
            }
        });
        return parkingVos;
    }
}

