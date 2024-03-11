package com.ykw.parking.mapper;

import com.ykw.parking.pojo.Admin;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;
@Mapper
public interface AdminMapper {
    public Admin queryAdmin(Admin admin);
    public int queryAllAdmin();
    @Select("select * from admin order by  `role` DESC")
    public List<Admin> queryAdminAll();
    @Select("select * from admin where admin_name=#{admin_name}")
    public Admin queryAdminByName(@Param("admin_name") String admin_name);
    public int addAdmin(Admin admin);
    @Delete("delete from admin where id=#{id}")
    public int delAdmin(@Param("id") String id);
    @Select("select * from admin where id=#{id}")
    public Admin queryAdminById(@Param("id") String id);
    public int updateAdmin(Admin admin);

    @Select("select * from admin where admin_name=#{admin_name} and admin_password=#{admin_password}")
    public Admin queryAdminByNameAndPwd(@Param("admin_name")String admin_name,@Param("admin_password")String admin_password);
}
