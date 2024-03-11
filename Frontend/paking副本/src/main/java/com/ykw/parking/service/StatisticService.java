package com.ykw.parking.service;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

/**
 * @Author: tang
 * @Date: 2021/09/05/20:28
 * @Description:
 */
@Service
public interface StatisticService {

    public int QueryAllParkingLot();

    public int QueryParkingLotAllOpen();

    public int QueryParkingLotAllIdentify();

    public int QueryParkingLotOpen();

    public int QueryParkingLotIdentify();

    public int QueryParkingAllLotSale();
    public int QueryParkingLotSale();
    public Double QueryOrderMoney(@Param("date") String date);
}
