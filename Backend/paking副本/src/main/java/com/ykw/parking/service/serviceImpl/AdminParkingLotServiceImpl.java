package com.ykw.parking.service.serviceImpl;

import com.ykw.parking.mapper.AdminParkingLotMapper;
import com.ykw.parking.pojo.ParkingLot;
import com.ykw.parking.service.AdminParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdminParkingLotServiceImpl implements AdminParkingLotService {
    @Autowired
    AdminParkingLotMapper adminParkingLotMapper;
    @Override
    public List<ParkingLot> queryParkingLotByPid(String pid) {
        return adminParkingLotMapper.queryParkingLotByPid(pid);
    }

    @Override
    public int delParkinglot(int id) {
        return adminParkingLotMapper.delParkinglot(id);
    }

    @Override
    public int queryParkingLotByNumAndPid(String number, String pid) {
        return adminParkingLotMapper.queryParkingLotByNumAndPid(number,pid);
    }

    @Override
    public int AddParkinglot(ParkingLot parkingLot) {
        return adminParkingLotMapper.AddParkinglot(parkingLot);
    }

    @Override
    public ParkingLot queryParkingLotById(int id) {
        return adminParkingLotMapper.queryParkingLotById(id);
    }

    @Override
    public int UpdateParkingLot(ParkingLot parkingLot) {
        return adminParkingLotMapper.UpdateParkingLot(parkingLot);
    }

    @Override
    public List<String> getPLpidByNumber(String pid) {
        return adminParkingLotMapper.getPLpidByNumber(pid);
    }

    @Override
    public int updateSale(String number, String pid) {
        return adminParkingLotMapper.updateSale(number,pid);
    }

    @Override
    public int updateSaleAndZero(String number, String pid) {
        return adminParkingLotMapper.updateSaleAndZero(number,pid);
    }

    @Override
    public ParkingLot queryParkingLotOne(String number, String pid) {
        return adminParkingLotMapper.queryParkingLotOne(number,pid);
    }

    @Override
    public int delParkinglotByPid(String pid) {
        return adminParkingLotMapper.delParkinglotByPid(pid);
    }
}
