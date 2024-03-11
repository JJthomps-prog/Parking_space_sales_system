package com.ykw.parking.mapper;

import com.ykw.parking.pojo.UserLoan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LoanMapper {
    @Select("select * from user_loan")
    public List<UserLoan> queryAllLoan();
    @Select("select count(*) from user_loan")
    public int queryAllLoanNum();
}
