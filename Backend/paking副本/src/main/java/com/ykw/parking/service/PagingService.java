package com.ykw.parking.service;

import com.ykw.parking.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface PagingService {
    public List<User> pagingUser(int pageNum,int pageSize );
}
