package com.ykw.parking.service;

import com.ykw.parking.pojo.Admin;
import com.ykw.parking.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {
    public Admin queryAdmin(Admin admin);
    public int queryAllAdmin();
    public List<Admin> queryAdminAll();
    public Admin queryAdminByName(@Param("admin_name") String admin_name);
    public int addAdmin(Admin admin);
    public int delAdmin(@Param("id") String id);
    public Admin queryAdminById(@Param("id") String id);
    public int updateAdmin(Admin admin);
    public Admin queryAdminByNameAndPwd(@Param("admin_name")String admin_name,@Param("admin_password")String admin_password);
}
