package com.ykw.parking.service;

import com.ykw.parking.pojo.UserParking;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserParkingService {
    public List<UserParking> QueryAllUserParking();
    public int QueryAllUserParkingIndex();
    public List<String> QueryParkingByCity();
    public int QueryBeUserParking(String uid,String number,String city,String address);
    public int addUserParking(UserParking userParking);
    public int  delUserParking(String uid,int pid, String number);
}
