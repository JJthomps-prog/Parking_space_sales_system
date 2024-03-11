package com.ykw.parking.mapper;

import com.ykw.parking.pojo.Parking;
import com.ykw.parking.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ParkingManageMapper {
    public List<Parking> queryParking();
    public int deleteParking(int value);
    public int queryParkingIndex();
    public Parking queryParkingByAddress(String address);
    public int AddParking(Parking parking);
    public Parking queryParkingById(int id);
    public int UpdateParking(Parking parking);
    public int getParkingIdByAddress(String address);
}
