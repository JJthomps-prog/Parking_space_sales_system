package com.ykw.parking.service.serviceImpl;

import com.ykw.parking.mapper.BackstageUserMapper;
import com.ykw.parking.pojo.User;
import com.ykw.parking.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AdminUserServiceImpl implements AdminUserService {
    @Autowired
    BackstageUserMapper backstageUserMapper;
    @Override
    public List<User> QueryAllUser() {
        return backstageUserMapper.QueryAllUser();
    }

    @Override
    public int DeleteUser(String value) {
        return backstageUserMapper.DeleteUser(value);
    }

    @Override
    public int AddUser(User user) {
        return backstageUserMapper.AddUser(user);
    }

    @Override
    public int QueryAllUserIndex(){
        return backstageUserMapper.QueryAllUserIndex();
    }

    @Override
    public int QueryAllUserByName(String name) {
        return backstageUserMapper.QueryAllUserByName(name);
    }

    @Override
    public int QueryAllUserByPhone(String phone_number) {
        return backstageUserMapper.QueryAllUserByPhone(phone_number);
    }

    @Override
    public User QueryOne(String id) {
        return backstageUserMapper.QueryOne(id);
    }

    @Override
    public int UpdateUser(User user) {
        return backstageUserMapper.UpdateUser(user);
    }

    @Override
    public List<User> QueryLimitUser(int offset, int rows) {
        return backstageUserMapper.QueryLimitUser(offset,rows);
    }

    @Override
    public int UpdateUserByState(int state, String id) {
        return backstageUserMapper.UpdateUserByState(state,id);
    }

    @Override
    public int QueryAllUserByEmail(String email) {
        return backstageUserMapper.QueryAllUserByEmail(email);
    }
}
