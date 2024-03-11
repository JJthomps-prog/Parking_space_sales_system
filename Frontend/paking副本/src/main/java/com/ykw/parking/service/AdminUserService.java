package com.ykw.parking.service;

import com.ykw.parking.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AdminUserService {
    public List<User> QueryAllUser();
    public int DeleteUser(String value);
    public int AddUser(User user);
    public int QueryAllUserIndex();
    public int QueryAllUserByName(String name);
    public int QueryAllUserByPhone(String phone_number);
    public User QueryOne(String id);
    public int UpdateUser(User user);
    public List<User> QueryLimitUser(int offset,int rows);
    public int UpdateUserByState(int  state,String id);
    public int QueryAllUserByEmail(String email);
}
