package com.ykw.parking.service;

import com.ykw.parking.pojo.ParkingLot;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;
//停车场信息
@Service
public interface AdminParkingLotService {
    public List<ParkingLot> queryParkingLotByPid(String pid);
    public int delParkinglot(int id);
    public int queryParkingLotByNumAndPid(String number,String pid);
    public int AddParkinglot(ParkingLot parkingLot);
    public ParkingLot queryParkingLotById(int id);
    public int UpdateParkingLot(ParkingLot parkingLot);
    public List<String> getPLpidByNumber(String pid);
    public int updateSale(String number,String pid);
    public int updateSaleAndZero(String number,String pid);
    public ParkingLot queryParkingLotOne(@Param("number") String number, @Param("pid") String pid);
    public int delParkinglotByPid(@Param("pid") String pid);
}
