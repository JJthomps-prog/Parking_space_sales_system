package com.ykw.parking.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ykw.parking.mapper.BackstageUserMapper;
import com.ykw.parking.pojo.User;
import com.ykw.parking.service.PagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PagingServiceImpl implements PagingService {
    @Autowired
    BackstageUserMapper backstageUserMapper;
    /*
    pageNum  第几页
    pageSize 一页多少条
    * */
    @Override
    public List<User> pagingUser(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<User> users = backstageUserMapper.QueryAllUser();
        PageInfo<User> pageInfo=new PageInfo<>(users);
        return pageInfo.getList();
    }
}
