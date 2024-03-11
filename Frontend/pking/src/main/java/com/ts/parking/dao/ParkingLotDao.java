package com.ts.parking.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ts.parking.pojo.ParkingLot;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ParkingLotDao extends BaseMapper<ParkingLot> {

    @Select("select * from parking_lot where id=#{id}")
    public ParkingLot queryById(Integer id);

    @Select("select * from parking_lot where pid=#{pid} and sale=#{sale} and open=#{open}")
    public List<ParkingLot> queryByPid(Integer pid, Integer sale,Integer open);

    @Update("update parking_lot set sale=#{sale} where id=#{id}")
    public int updState(Integer sale,Integer id);
}
