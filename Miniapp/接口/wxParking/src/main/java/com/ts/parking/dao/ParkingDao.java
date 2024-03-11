package com.ts.parking.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ts.parking.pojo.Parking;
import com.ts.parking.pojo.ParkingLot;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: tang
 * @Date: 2021/10/20/20:28
 * @Description:
 */
@Mapper
public interface ParkingDao  extends BaseMapper<Parking>{

    @Select("select * from parking_lot where pid=#{pid} and sale=#{sale}")
    public List<ParkingLot> queryByPid(Integer pid,Integer sale);


    @Select("select * from parking_one where id=#{id}")
    public Parking queryByid(Integer id);
    List<Parking> queryByNameLike(String like);
}
