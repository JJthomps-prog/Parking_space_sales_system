package com.ykw.parking.service.serviceImpl;

import com.ykw.parking.mapper.StatisticMapper;
import com.ykw.parking.service.SearchUserService;
import com.ykw.parking.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: tang
 * @Date: 2021/09/05/20:28
 * @Description:
 */
@Service
public class StatisticServiceImpl implements StatisticService {
    @Autowired
    StatisticMapper statisticMapper;
    @Override
    public int QueryAllParkingLot() {
        return statisticMapper.QueryAllParkingLot();
    }

    @Override
    public int QueryParkingLotAllOpen() {
        return statisticMapper.QueryParkingLotAllOpen();
    }

    @Override
    public int QueryParkingLotAllIdentify() {
        return statisticMapper.QueryParkingLotAllIdentify();
    }

    @Override
    public int QueryParkingLotOpen() {
        return statisticMapper.QueryParkingLotOpen();
    }

    @Override
    public int QueryParkingLotIdentify() {
        return statisticMapper.QueryParkingLotIdentify();
    }

    @Override
    public int QueryParkingAllLotSale() {
        return statisticMapper.QueryParkingAllLotSale();
    }

    @Override
    public int QueryParkingLotSale() {
        return statisticMapper.QueryParkingLotSale();
    }

    @Override
    public Double QueryOrderMoney(String date) {
        return statisticMapper.QueryOrderMoney(date);
    }
}
