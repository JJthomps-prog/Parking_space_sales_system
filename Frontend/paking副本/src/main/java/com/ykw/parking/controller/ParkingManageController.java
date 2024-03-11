package com.ykw.parking.controller;

import com.ykw.parking.pojo.Parking;
import com.ykw.parking.pojo.ParkingLot;
import com.ykw.parking.pojo.UserOrder;
import com.ykw.parking.service.AdminParkingLotService;
import com.ykw.parking.service.ParkingManageService;
import com.ykw.parking.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;
import java.util.List;
@Controller()
public class ParkingManageController {
    @Autowired
    ParkingManageService parkingManageService;

    @Autowired
    AdminParkingLotService adminParkingLotService;

    @Autowired
    UserOrderService userOrderService;
    /**
     * @return 返回 parking-add.html页面
     */
    @RequestMapping("/parking-add")
    public String parkingadd(HttpSession session){
        return "parking-add";
    }

    /**
     * @return 返回 parking-edit.html页面
     */
    @RequestMapping("/parking-edit")
    public String parkingedit(HttpSession session){
        return "parking-edit";
    }

    /**
     * @return 返回 parkinglot-add.html页面
     */
    @RequestMapping("/parkinglot-add")
    public String parkinglotadd(HttpSession session){
        return "parkinglot-add";
    }


    /**
     * @return 返回 parkinglot-edit.html页面
     */
    @RequestMapping("/parkinglot-edit")
    public String parkinglotedit(HttpSession session){
        return "parkinglot-edit";
    }

    /**
     *
     * @param session
     * @return 返回parking-list.html页面
     */
    @RequestMapping("/parkinglist")
    public String getParkingList(HttpSession session){
        List<Parking> parkings = parkingManageService.queryParking();
        session.setAttribute("parking",parkings);
        session.setAttribute("parkingIndex",parkingManageService.queryParkingIndex());
        return "parking-list";
    }

    /**
     *
     * @param value
     * @param session
     * @return 删除车位
     */
    @RequestMapping("/admin/delParking")
    @ResponseBody
    public String deleteParking(String value,HttpSession session){
        Integer integer = Integer.valueOf(value.trim());
        int i = parkingManageService.deleteParking(integer);
        if(i==1){
            int parkingIndex=(Integer)session.getAttribute("parkingIndex");
            session.setAttribute("parkingIndex",parkingIndex-1);
            adminParkingLotService.delParkinglotByPid(value.trim());
            userOrderService.delOrderByPid(integer);
            return "1";
        }
        return "删除失败！";
    }

    @RequestMapping("/admin/addparking")
    @ResponseBody
    public String addparking(Parking parking,HttpSession session){
        System.out.println(parking);
        Parking pk = parkingManageService.queryParkingByAddress(parking.getAddress());
        if(pk!=null){
            return "地址已存在！";
        }
        parkingManageService.AddParking(parking);
        return null;
    }

    @RequestMapping("/admin/updateParkingBeam")
    @ResponseBody
    public void updateParkingBeam(int id,HttpSession session){
        Parking parking = parkingManageService.queryParkingById(id);
        session.setAttribute("UpdateParkingBean",parking);
    }

    @RequestMapping("/admin/updateParkingLotBeam")
    @ResponseBody
    public void updateParkingLotBeam(int id,HttpSession session){
        ParkingLot parkingLotBean = adminParkingLotService.queryParkingLotById(id);

        session.setAttribute("UpdateParkingLotBean",parkingLotBean);
    }

    /**
     *
     * @param parking
     * @param session
     * @return 对车位进行编辑
     */
    @RequestMapping("/admin/updateParking")
    @ResponseBody
    public String updateParking(Parking parking,HttpSession session){
        Parking parkingbean=(Parking) session.getAttribute("UpdateParkingBean");
        if(parking.getNumber()==parkingbean.getNumber()){
            if(parking.getOwner_phone()==parkingbean.getOwner_phone()){
                if(parking.getOwner_name()==parkingbean.getOwner_name()){
                    return null;
                }
            }
        }
        int i = parkingManageService.UpdateParking(parking);
        if(i>0){
            return null;
        }else{
            return "编辑失败！";
        }
    }

    /**
     *
     * @param id
     * @param session
     * @return 返回每个车位对应的信息列表
     */
    @RequestMapping("/parkinglot")
    public String parkinglot(int id,HttpSession session){
        List<ParkingLot> parkingLots = adminParkingLotService.queryParkingLotByPid(id + "");
        Parking parkingMSG = parkingManageService.queryParkingById(id);
        session.setAttribute("parkinglotList",parkingLots);
        session.setAttribute("parkingMSG",parkingMSG);
        return "parkinglot-list";

    }

    /**
     *
     * @param id
     * @param session
     * @return 删除车位对应的一个信息列表
     */
    @RequestMapping("/admin/delParkinglot")
    @ResponseBody
    public String delParkinglot(String id,HttpSession session){
        Parking parkingMSG = (Parking)session.getAttribute("parkingMSG");
        String index=id.trim();
        int i = adminParkingLotService.delParkinglot(Integer.valueOf(index));
        if(i>0){
            userOrderService.delOrder(Integer.valueOf(index));
            int number = parkingMSG.getNumber();
            parkingMSG.setNumber(number-1);
            session.setAttribute("parkingMSG",parkingMSG);
            parkingManageService.UpdateParking(parkingMSG);
            return "1";
        }else{
            return "删除失败！";
        }
    }

    /**
     *
     * @param parkingLot
     * @param session
     * @return 添加车位信息
     */
    @PostMapping("/admin/addparkinglot2")
    @ResponseBody
    public String addparkinglot(ParkingLot parkingLot,HttpSession session){
        Parking parkingMSG = (Parking)session.getAttribute("parkingMSG");
        System.out.println(parkingLot);
        int i = adminParkingLotService.
                queryParkingLotByNumAndPid(parkingLot.getNumber(), parkingLot.getPid());
        if(i>0){
            return "编号已存在！";
        }
        int number = parkingMSG.getNumber();
        adminParkingLotService.AddParkinglot(parkingLot);
        parkingMSG.setNumber(number+1);
        session.setAttribute("parkingMSG",parkingMSG);
        parkingManageService.UpdateParking(parkingMSG);
        return null;
    }


    /**
     * @param number
     * @param price
     * @param explain
     * @param identify
     * @param open
     * @param sale
     * @param session
     * @return 更新车位信息
     */
    @PostMapping("/admin/updateParkingLot")
    @ResponseBody
    public  String updateParkingLot(String number,Double price,String explain,
                                    Integer identify,Integer open,Integer sale,HttpSession session){
        ParkingLot parkingLotBean=(ParkingLot) session.getAttribute("UpdateParkingLotBean");
        System.out.println(number+"    "+parkingLotBean.getNumber());
        if(!number.equals(parkingLotBean.getNumber())) {
            int jy = adminParkingLotService.queryParkingLotByNumAndPid(number, parkingLotBean.getPid());
            if (jy > 0) {
                return "该编号以存在！";
            }
        }
        if(open!=null){
            parkingLotBean.setOpen(open);
        }
        if(identify!=null){
            parkingLotBean.setIdentify(identify);
        }
        if(sale!=null){
            parkingLotBean.setSale(sale);
        }
        parkingLotBean.setNumber(number);
        parkingLotBean.setPrice(price);
        parkingLotBean.setExplain(explain);
        int i = adminParkingLotService.UpdateParkingLot(parkingLotBean);
        if(i>0){
            userOrderService.updateOrder(number,price,parkingLotBean.getId());
            return null;
        }else
        {
            return "编辑失败";
        }

    }
}
