package com.ykw.parking.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @Author: tang
 * @Date: 2021/08/31/21:02
 * @Description: 定时任务
 */
@Service
public class ScheduledService {
    @Scheduled(cron = "0 0/2 * * * ?") //表示每2分钟 执行任务
    public void hello(){
        System.out.println("hello.....");
    }
}
