package com.ts.parking.dao;

import com.ts.parking.pojo.Bank;
import com.ts.parking.pojo.ShoppingCart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


@Mapper
public interface ShoppingCartDao {

    @Select("select * from shoppingcart where userid=#{userid}")
    public List<ShoppingCart> getMyCart(String userid);

    @Delete("delete from shoppingcart where userid=#{userid} and parkingid=#{parkingid}")
    public int del(String userid ,String parkingid);

    @Select("select * from bank where user=#{user}")
    public Bank queryOne(String user);

    @Update("update bank set money=#{money} where user=#{user}")
    public int updBank(String user,Double money);
}
