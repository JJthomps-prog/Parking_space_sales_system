package com.ykw.parking.service;

import com.ykw.parking.pojo.UserOrder;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public interface UserOrderService {
    public List<UserOrder> QueryAllOrder();
    public int QueryAllOrderIndex();
    public int addOrder(UserOrder userOrder);
    public UserOrder QueryOneByNumber(@Param("number") String number);
    public int updateOrder(@Param("number") String number,@Param("price") double price,@Param("pid") int pid);
    public UserOrder QueryByPlId(@Param("plid") int plid);
    public int delOrder(@Param("plid") int plid);
    public int delOrderByPid(@Param("lid") int pid);
    public int delOrderByOid(@Param("oid") String oid);
    public List<UserOrder> QueryOrderByDate(@Param("date") String date);
    public List<UserOrder> QueryOrderByState(@Param("state") String state);
    public List<UserOrder> QueryOrderByStateAndDate(@Param("state") String state ,@Param("date") String date);
    public int QueryOrderByStateTwo(@Param("state") int state);
}
