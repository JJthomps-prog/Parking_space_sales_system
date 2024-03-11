package com.ykw.parking.service.serviceImpl;

import com.ykw.parking.mapper.UserParkingMapper;
import com.ykw.parking.pojo.UserParking;
import com.ykw.parking.service.UserParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserParkingServiceImpl implements UserParkingService {
    @Autowired
    UserParkingMapper userParkingMapper;
    @Override
    public List<UserParking> QueryAllUserParking() {
        return userParkingMapper.QueryAllUserParking();
    }

    @Override
    public int QueryAllUserParkingIndex() {
        return userParkingMapper.QueryAllUserParkingIndex();
    }

    @Override
    public List<String> QueryParkingByCity() {
        return userParkingMapper.QueryParkingByCity();
    }

    @Override
    public int QueryBeUserParking(String uid, String number, String city, String address) {
        return userParkingMapper.QueryBeUserParking(uid,number,city,address);
    }

    @Override
    public int addUserParking(UserParking userParking) {
        return userParkingMapper.addUserParking(userParking);
    }

    @Override
    public int delUserParking(String uid, int pid, String number) {
        return userParkingMapper.delUserParking(uid,pid,number);
    }
}
