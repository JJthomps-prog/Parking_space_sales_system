package com.ykw.parking.service;

import com.ykw.parking.pojo.Parking;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ParkingManageService {
    public List<Parking> queryParking();
    public int deleteParking(int value);
    public int queryParkingIndex();
    public Parking queryParkingByAddress(String address);
    public int AddParking(Parking parking);
    public Parking queryParkingById(int id);
    public int UpdateParking(Parking parking);
    public int getParkingIdByAddress(String address);
}
