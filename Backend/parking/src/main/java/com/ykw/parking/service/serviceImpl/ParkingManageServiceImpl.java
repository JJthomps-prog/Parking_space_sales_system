package com.ykw.parking.service.serviceImpl;

import com.ykw.parking.mapper.ParkingManageMapper;
import com.ykw.parking.pojo.Parking;
import com.ykw.parking.service.ParkingManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ParkingManageServiceImpl implements ParkingManageService {
    @Autowired
    ParkingManageMapper parkingManageMapper;
    @Override
    public List<Parking> queryParking() {
        return parkingManageMapper.queryParking();
    }

    @Override
    public int deleteParking(int value) {
        return parkingManageMapper.deleteParking(value);
    }

    @Override
    public int queryParkingIndex() {
        return parkingManageMapper.queryParkingIndex();
    }

    @Override
    public Parking queryParkingByAddress(String address) {
        return parkingManageMapper.queryParkingByAddress(address);
    }

    @Override
    public int AddParking(Parking parking) {
        return parkingManageMapper.AddParking(parking);
    }

    @Override
    public Parking queryParkingById(int id) {
        return parkingManageMapper.queryParkingById(id);
    }

    @Override
    public int UpdateParking(Parking parking) {
        return parkingManageMapper.UpdateParking(parking);
    }

    @Override
    public int getParkingIdByAddress(String address) {
        return parkingManageMapper.getParkingIdByAddress(address);
    }
}
