package com.ykw.parking.service.serviceImpl;

import com.ykw.parking.mapper.AdminMapper;
import com.ykw.parking.pojo.Admin;
import com.ykw.parking.pojo.User;
import com.ykw.parking.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminMapper adminMapper;

    @Override
    public Admin queryAdmin(Admin admin) {
        return adminMapper.queryAdmin(admin);
    }

    @Override
    public int queryAllAdmin() {
        return adminMapper.queryAllAdmin();
    }

    @Override
    public List<Admin> queryAdminAll() {
        return adminMapper.queryAdminAll();
    }

    @Override
    public Admin queryAdminByName(String admin_name) {
        return adminMapper.queryAdminByName(admin_name);
    }

    @Override
    public int addAdmin(Admin admin) {
        return adminMapper.addAdmin(admin);
    }

    @Override
    public int delAdmin(String id) {
        return adminMapper.delAdmin(id);
    }

    @Override
    public Admin queryAdminById(String id) {
        return adminMapper.queryAdminById(id);
    }

    @Override
    public int updateAdmin(Admin admin) {
        return adminMapper.updateAdmin(admin);
    }

    @Override
    public Admin queryAdminByNameAndPwd(String admin_name, String admin_password) {
        return adminMapper.queryAdminByNameAndPwd(admin_name,admin_password);
    }

}
