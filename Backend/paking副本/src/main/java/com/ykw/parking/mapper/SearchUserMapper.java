package com.ykw.parking.mapper;

import com.ykw.parking.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SearchUserMapper {
    public List<User> QueryUserByName(String name);
    public List<User> QueryUserByPhone(String phone);
    public List<User> QueryUserByEmail(String email);
    public int QueryUserByNameInt(String name);
    public int QueryUserByPhoneInt(String phone);
    public int QueryUserByEmailInt(String email);
    public List<User> QueryUserByNameAndPhone(String name,String phone);
    public List<User> QueryUserByNameAndEmail(String name,String email);
    public List<User> QueryUserByPhoneAndEmail(String phone,String email);
    public int QueryUserByNameAndPhoneInt(String name,String phone);
    public int QueryUserByNameAndEmailInt(String name,String email);
    public int QueryUserByPhoneAndEmailInt(String phone,String email);
    public List<User> QueryUserByNameAndPhoneAndEmail(String name,String phone,String email);
    public int QueryUserByNameAndPhoneAndEmailInt(String name,String phone,String email);


}
