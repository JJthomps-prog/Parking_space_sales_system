package com.ykw.parking.service.serviceImpl;

import com.ykw.parking.mapper.RealNameMapper;
import com.ykw.parking.pojo.RealUser;
import com.ykw.parking.service.RealNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RealNameServiceImpl implements RealNameService {
    @Autowired
    RealNameMapper realNameMapper;
    @Override
    public List<RealUser> queryAllRealUser() {
        return realNameMapper.queryAllRealUser();
    }

    @Override
    public int delRealNameById(String id) {
        return realNameMapper.delRealNameById(id);
    }

    @Override
    public int queryAllRealUserIndex() {
        return realNameMapper.queryAllRealUserIndex();
    }

    @Override
    public List<RealUser> searchRealUser(String id) {
        return realNameMapper.searchRealUser(id);
    }

    @Override
    public int searchRealUserIndex(String id) {
        return realNameMapper.searchRealUserIndex(id);
    }

    @Override
    public int queryRealUserById(String id) {
        return realNameMapper.queryRealUserById(id);
    }

    @Override
    public int queryRealUserByNum(String id_number) {
        return realNameMapper.queryRealUserByNum(id_number);
    }

    @Override
    public int AddRealUser(RealUser realUser) {
        return realNameMapper.AddRealUser(realUser);
    }

    @Override
    public RealUser queryRealUser(String id) {
        return realNameMapper.queryRealUser(id);
    }

    @Override
    public int updateEmailAndPhone(String email, String phone, String id) {
        return realNameMapper.updateEmailAndPhone(email,phone,id);
    }
}
