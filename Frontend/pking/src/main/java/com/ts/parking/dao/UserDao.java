package com.ts.parking.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ts.parking.pojo.RealUser;
import com.ts.parking.pojo.User;
import com.ts.parking.pojo.UserOwner;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface UserDao extends BaseMapper<User> {
    @Select("select * from user where email=#{email} and password=#{password}")
    public User queryUserLogin(String email,String password);

    @Select("select * from real_user where id=#{id}")
    public RealUser queryReaUser(String id);

    @Select("select * from user_owner where uid=#{uid}")
    public UserOwner queryUserOwner(String uid);

    @Select("select * from real_user where id=#{uid}")
    public RealUser queryUserR(String uid);
}
