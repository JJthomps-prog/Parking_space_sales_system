package com.ykw.parking.mapper;

import com.ykw.parking.pojo.Parking;
import com.ykw.parking.pojo.ParkingLot;
import com.ykw.parking.pojo.UserOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CountEachMapper {
    @Select("select * from parking_one where id=#{id}")
    public Parking getOneParking(Integer id);

    @Select("select * from parking_lot where pid=#{pid}")
    public List<ParkingLot> getParkingLot(Integer pid);

    public List<UserOrder> getUserOrder(Integer pid,String startTime,String endTime,int state);
}
