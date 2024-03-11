package com.ykw.parking.mapper;

import com.ykw.parking.pojo.UserOrder;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface UserOrderMapper {
    @Select("select * from user_order order by `date`  DESC")
    public List<UserOrder> QueryAllOrder();

    @Select("select count(*) from user_order")
    public int QueryAllOrderIndex();

    @Select("select * from user_order where plid=#{plid}")
    public UserOrder QueryByPlId(@Param("plid") int plid);

    public int addOrder(UserOrder userOrder);

    @Select("select * from user_order where number=#{number}")
    public UserOrder QueryOneByNumber(@Param("number") String number);

    @Update("update user_order set number=#{number},price=#{price} where plid=#{plid}")
    public int updateOrder(@Param("number") String number,@Param("price") double price,@Param("plid") int plid);

    @Delete("delete from user_order where plid=#{plid}")
    public int delOrder(@Param("plid") int plid);

    @Delete("delete from user_order where pid=#{pid}")
    public int delOrderByPid(@Param("pid") int pid);

    @Delete("delete from user_order where oid=#{oid}")
    public int delOrderByOid(@Param("oid") String oid);

    @Select("select * from user_order where date=#{date}")
    public List<UserOrder> QueryOrderByDate(@Param("date") String date);

    @Select("select * from user_order where state=#{state}")
    public List<UserOrder> QueryOrderByState(@Param("state") String state);

    @Select("select count(*) from user_order where state=#{state}")
    public int QueryOrderByStateTwo(@Param("state") int state);

    @Select("select * from user_order where state=#{state} and date=#{date}")
    public List<UserOrder> QueryOrderByStateAndDate(@Param("state") String state ,@Param("date") String date);

}
