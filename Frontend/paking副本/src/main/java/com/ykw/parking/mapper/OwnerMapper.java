package com.ykw.parking.mapper;

import com.ykw.parking.pojo.UserOwner;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.data.relational.core.sql.In;

import java.util.List;

@Mapper
public interface OwnerMapper {
    @Select("select * from user_owner")
    public List<UserOwner> queryAllOwner();

    @Select("select count(*) from user_owner")
    public Integer queryAllOwnerIndex();

    @Select("select * from user_owner where uid=#{uid}")
    public UserOwner queryOwnerByUid(String uid);

    @Update("update user_owner set state=#{state} where uid=#{uid}")
    public int  updateOwnerByState(String uid,Integer state);

    @Select("select count(*) from parking_lot")
    public  int queryAllParkingNumber();

    @Select("select count(*) from user_parking")
    public  int queryAllUserP();
}
