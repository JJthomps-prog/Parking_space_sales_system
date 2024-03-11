package com.ykw.parking.mapper;

import com.ykw.parking.pojo.ParkingLot;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdminParkingLotMapper {
    public List<ParkingLot> queryParkingLotByPid(String pid);
    public int delParkinglot(int id);
    public int queryParkingLotByNumAndPid(String number,String pid);
    public int AddParkinglot(ParkingLot parkingLot);
    public ParkingLot queryParkingLotById(int id);
    public int UpdateParkingLot(ParkingLot parkingLot);
    public List<String> getPLpidByNumber(String pid);
    public int updateSale(String number,String pid);
    public int updateSaleAndZero(String number,String pid);
    @Select("select * from parking_lot where pid=#{pid} and number=#{number}")
    public ParkingLot queryParkingLotOne(@Param("number") String number,@Param("pid") String pid);

    @Delete("delete from parking_lot where pid=#{pid}")
    public int delParkinglotByPid(@Param("pid") String pid);
}
