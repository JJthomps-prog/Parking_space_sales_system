package com.ykw.parking.service.serviceImpl;

import com.ykw.parking.service.RedisStringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisStringServiceImpl implements RedisStringService {
    @Autowired
    public RedisTemplate redisTemplate;
    @Override
    public void redisString() {
        //设置指
        redisTemplate.opsForValue().set("key1","key01");
        redisTemplate.opsForValue().set("key2","key02");

/*        //获得指
        String key1 =(String) redisTemplate.opsForValue().get("key1");

        //设置新值并返回旧指
        String andSet =(String) redisTemplate.opsForValue().getAndSet("key1", "nihao");

        //删除键
        redisTemplate.delete("key1");*/
    }
}
