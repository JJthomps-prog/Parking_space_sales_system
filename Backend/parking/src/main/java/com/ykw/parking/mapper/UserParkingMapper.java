package com.ykw.parking.mapper;

import com.ykw.parking.pojo.User;
import com.ykw.parking.pojo.UserParking;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserParkingMapper {
    public List<UserParking> QueryAllUserParking();
    public int QueryAllUserParkingIndex();
    public List<String> QueryParkingByCity();
    public int QueryBeUserParking(String uid,String number,String city,String address);
    public int addUserParking(UserParking userParking);
    public int  delUserParking(String uid,int pid, String number);
}
