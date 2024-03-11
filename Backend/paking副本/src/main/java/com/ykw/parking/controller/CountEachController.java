package com.ykw.parking.controller;

import com.ykw.parking.mapper.CountEachMapper;
import com.ykw.parking.pojo.ParkingLot;
import com.ykw.parking.pojo.UserOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class CountEachController {
    @Autowired
    CountEachMapper countEachMapper;
    @RequestMapping("/countEach")
    public  String CountEach(@RequestParam("id") Integer id, Model model,HttpSession session){
        List<String> data =new ArrayList<>();
        List<String> dataBaen =new ArrayList<>();
        List<Double> price=new ArrayList<>();
        price.clear();
        dataBaen.clear();
        data.clear();
        SimpleDateFormat sdf=new SimpleDateFormat("MM");
        List<ParkingLot> list = countEachMapper.getParkingLot(id);
        model.addAttribute("parkingNumber",list.size());
        int Sale=0;
        int open=0;
        int Identify=0;
        for (ParkingLot parkingLot : list) {
            if(parkingLot.getSale()==1){
                Sale++;
            }
            if (parkingLot.getOpen()==1){
                open++;
            }
            if(parkingLot.getIdentify()==1){
                Identify++;
            }
        }
        model.addAttribute("SaleNumber",Sale);
        model.addAttribute("openNumber",open);
        model.addAttribute("IdentifyNumber",Identify);
        for(int i=0;i<7;i++){
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(new Date());
            calendar.add(calendar.MONTH,-i);
            data.add(sdf.format(calendar.getTime())+"月");
            List<UserOrder> userOrder = countEachMapper.getUserOrder(id, "2021-" + sdf.format(calendar.getTime()) + "-01", "2021-" + sdf.format(calendar.getTime()) + "-28", 1);

            ArrayList collect = (ArrayList) userOrder.stream().map((itme) -> {
                return itme.getPrice();
            }).collect(Collectors.toList());
            Double p=0.0;
            for(int j=0;j<collect.size();j++){
                p=p+((Double)collect.get(j));
            }
            price.add(p);
            p=0.0;
        }

        session.setAttribute("date",data);
        session.setAttribute("parkingSale",price);
        return "count-each";
    }
}
