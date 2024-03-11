package com.ykw.parking.mapper;

import com.ykw.parking.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface BackstageUserMapper {
    public List<User> QueryAllUser();
    public int DeleteUser(String value);
    public int AddUser(User user);
    public int QueryAllUserIndex();
    public int QueryAllUserByName(String name);
    public int QueryAllUserByPhone(String phone_number);

    @Select("select count(*) from user where email=#{email}")
    public int QueryAllUserByEmail(String email);
    public User QueryOne(String id);
    public int UpdateUser(User user);
    public List<User> QueryLimitUser(int offset,int rows);
    public int UpdateUserByState(int  state,String id);
}
