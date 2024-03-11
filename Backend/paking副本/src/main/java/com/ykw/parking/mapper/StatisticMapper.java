package com.ykw.parking.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Author: tang
 * @Date: 2021/09/05/20:20
 * @Description: 统计mapper
 */
@Mapper
public interface StatisticMapper {
    @Select("select count(*) from parking_lot")
    public int QueryAllParkingLot();

    @Select("select count(*) from parking_lot where open=1")
    public int QueryParkingLotAllOpen();

    @Select("select count(*) from parking_lot where identify=1")
    public int QueryParkingLotAllIdentify();

    @Select("select count(*) from parking_lot where open=0 and sale=0")
    public int QueryParkingLotOpen();

    @Select("select count(*) from parking_lot where identify=0 and sale=0")
    public int QueryParkingLotIdentify();

    @Select("select count(*) from parking_lot  where sale=1")
    public int QueryParkingAllLotSale();

    @Select("select count(*) from parking_lot  where sale=0")
    public int QueryParkingLotSale();

    @Select("select sum(price) from user_order where date=#{date} and state=1")
    public Double QueryOrderMoney(@Param("date") String date);


}
