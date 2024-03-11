package com.ts.parking.dao;

import com.ts.parking.pojo.UserOrder;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Author: tang
 * @Date: 2021/10/25/17:38
 * @Description:
 */
@Mapper
public interface UserOrderDao {

    public int addOrder(UserOrder userOrder);

    @Select("select * from user_order where uid=#{uid} and state=#{i} ")
    public List<UserOrder> getAllOrder(String uid,String i);

    @Update("update user_order set state=#{i} where oid=#{oid}")
    public void delete(String oid,String i);
    @Select("select * from user_order where oid=#{oid}")
    public UserOrder getOne(String oid);
}
