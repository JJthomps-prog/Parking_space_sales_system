package com.ykw.parking.service.serviceImpl;

import com.ykw.parking.mapper.UserOrderMapper;
import com.ykw.parking.pojo.UserOrder;
import com.ykw.parking.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserOrderServiceImpl implements UserOrderService {
    @Autowired
    UserOrderMapper userOrderMapper;
    @Override
    public List<UserOrder> QueryAllOrder() {
        return userOrderMapper.QueryAllOrder();
    }

    @Override
    public int QueryAllOrderIndex() {
        return userOrderMapper.QueryAllOrderIndex();
    }

    @Override
    public int addOrder(UserOrder userOrder) {
        return userOrderMapper.addOrder(userOrder);
    }

    @Override
    public UserOrder QueryOneByNumber(String number) {
        return userOrderMapper.QueryOneByNumber(number);
    }

    @Override
    public int updateOrder(String number, double price, int pid) {
        return userOrderMapper.updateOrder(number,price,pid);
    }

    @Override
    public UserOrder QueryByPlId(int plid) {
        return userOrderMapper.QueryByPlId(plid);
    }

    @Override
    public int delOrder(int plid) {
        return userOrderMapper.delOrder(plid);
    }

    @Override
    public int delOrderByPid(int pid) {
        return userOrderMapper.delOrderByPid(pid);
    }

    @Override
    public int delOrderByOid(String oid) {
        return userOrderMapper.delOrderByOid(oid);
    }

    @Override
    public List<UserOrder> QueryOrderByDate(String date) {
        return userOrderMapper.QueryOrderByDate(date);
    }

    @Override
    public List<UserOrder> QueryOrderByState(String state) {
        return userOrderMapper.QueryOrderByState(state);
    }

    @Override
    public List<UserOrder> QueryOrderByStateAndDate(String state, String date) {
        return userOrderMapper.QueryOrderByStateAndDate(state,date);
    }

    @Override
    public int QueryOrderByStateTwo(int state) {
        return userOrderMapper.QueryOrderByStateTwo(state);
    }

}
