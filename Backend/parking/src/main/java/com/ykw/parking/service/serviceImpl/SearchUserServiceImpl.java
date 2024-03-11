package com.ykw.parking.service.serviceImpl;

import com.ykw.parking.mapper.SearchUserMapper;
import com.ykw.parking.pojo.User;
import com.ykw.parking.service.SearchUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchUserServiceImpl implements SearchUserService {
    @Autowired
    SearchUserMapper searchUserMapper;
    @Override
    public List<User> QueryUserByName(String name) {
        return searchUserMapper.QueryUserByName(name);
    }

    @Override
    public List<User> QueryUserByPhone(String phone) {
        return searchUserMapper.QueryUserByPhone(phone);
    }

    @Override
    public List<User> QueryUserByEmail(String email) {
        return searchUserMapper.QueryUserByEmail(email);
    }

    @Override
    public int QueryUserByNameInt(String name) {
        return searchUserMapper.QueryUserByNameInt(name);
    }

    @Override
    public int QueryUserByPhoneInt(String phone) {
        return searchUserMapper.QueryUserByPhoneInt(phone);
    }

    @Override
    public int QueryUserByEmailInt(String email) {
        return searchUserMapper.QueryUserByEmailInt(email);
    }

    @Override
    public List<User> QueryUserByNameAndPhone(String name, String phone) {
        return searchUserMapper.QueryUserByNameAndPhone(name, phone);
    }

    @Override
    public List<User> QueryUserByNameAndEmail(String name, String email) {
        return searchUserMapper.QueryUserByNameAndEmail(name,email);
    }

    @Override
    public List<User> QueryUserByPhoneAndEmail(String phone, String email) {
        return searchUserMapper.QueryUserByPhoneAndEmail(phone,email);
    }

    @Override
    public int QueryUserByNameAndPhoneInt(String name, String phone) {
        return searchUserMapper.QueryUserByNameAndPhoneInt(name,phone);
    }

    @Override
    public int QueryUserByNameAndEmailInt(String name, String email) {
        return searchUserMapper.QueryUserByNameAndEmailInt(name,email);
    }

    @Override
    public int QueryUserByPhoneAndEmailInt(String phone, String email) {
        return searchUserMapper.QueryUserByPhoneAndEmailInt(phone,email);
    }

    @Override
    public List<User> QueryUserByNameAndPhoneAndEmail(String name, String phone, String email) {
        return searchUserMapper.QueryUserByNameAndPhoneAndEmail(name,phone,email);
    }

    @Override
    public int QueryUserByNameAndPhoneAndEmailInt(String name, String phone, String email) {
        return searchUserMapper.QueryUserByNameAndPhoneAndEmailInt(name,phone,email);
    }
}
