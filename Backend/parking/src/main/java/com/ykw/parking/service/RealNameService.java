package com.ykw.parking.service;

import com.ykw.parking.pojo.RealUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface RealNameService {
    public List<RealUser> queryAllRealUser();
    public int delRealNameById(String id);
    public int queryAllRealUserIndex();
    public List<RealUser> searchRealUser(String id);
    public int searchRealUserIndex(String id);
    public int queryRealUserById(String id);
    public int queryRealUserByNum(String id_number);
    public int AddRealUser(RealUser realUser);
    public RealUser queryRealUser(String id);
    public int updateEmailAndPhone(@Param("email") String email, @Param("phone_number") String phone, @Param("id") String id);
}
