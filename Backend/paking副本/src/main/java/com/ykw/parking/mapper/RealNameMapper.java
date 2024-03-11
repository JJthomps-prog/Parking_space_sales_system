package com.ykw.parking.mapper;

import com.ykw.parking.pojo.RealUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface RealNameMapper {
    public List<RealUser> queryAllRealUser();
    public int delRealNameById(String id);
    public int queryAllRealUserIndex();
    public List<RealUser> searchRealUser(String id);
    public int searchRealUserIndex(String id);
    public int queryRealUserById(String id);
    public int queryRealUserByNum(String id_number);
    public int AddRealUser(RealUser realUser);
    public RealUser queryRealUser(String id);
    @Update("update real_user set email=#{email},phone_number=#{phone_number} where id=#{id}")
    public int updateEmailAndPhone(@Param("email") String email,@Param("phone_number") String phone,@Param("id") String id);
}
