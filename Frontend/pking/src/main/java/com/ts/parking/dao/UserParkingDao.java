package com.ts.parking.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ts.parking.pojo.RenChou;
import com.ts.parking.pojo.ShoppingCart;
import com.ts.parking.pojo.UserParking;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface UserParkingDao  {

    @Select("select * from renchou where user_id=#{user_id}")
    public List<RenChou> renchou(String user_id);

    @Insert("insert into renchou values(#{parkingLot_id},#{user_id})")
    public int Addrenchou(String parkingLot_id,String user_id);

    @Select("select * from shoppingcart where parkingid=#{parkingid} and userid=#{userid}")
    public ShoppingCart querySCOne(String parkingid,String userid);

    @Insert("insert into shoppingcart values(#{parkingid},#{userid})")
    public int AddSC(String parkingid,String userid);
}
