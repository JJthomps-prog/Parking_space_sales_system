package com.ykw.parking.controller;

import com.ykw.parking.service.StatisticService;
import com.ykw.parking.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: tang
 * @Date: 2021/09/05/13:53
 * @Description: 统计
 */
@Controller()
public class StatisticController {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    UserOrderService userOrderService;
    @Autowired
   public RedisTemplate<String, Object> redisTemplate;

    @Autowired
    StatisticService statisticService;

     /**
       * @Author tang
       * @Date 2021/9/7
       * @Description: 车位统计功能
      */
    @RequestMapping("/count")
    public  String countList(HttpSession session){
        SimpleDateFormat sdf=new SimpleDateFormat("MM-dd");
        List<String> list =new ArrayList<>();
        List<Integer> sales =new ArrayList<>();
        List<Integer> statistic =new ArrayList<>();
        list.clear();
        sales.clear();
        statistic.clear();
        for(int i=0;i<7;i++){
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(new Date());
            calendar.add(calendar.DATE,-i);
            list.add(sdf.format(calendar.getTime()));
            String value=stringRedisTemplate.opsForValue().
                    get(sdf.format(calendar.getTime()));
            if(value==null){
                sales.add(0);
            }else{
                sales.add(Integer.valueOf(value));
            }

        }
        statistic.add(statisticService.QueryAllParkingLot());
        statistic.add(statisticService.QueryParkingLotSale());
        statistic.add(statisticService.QueryParkingAllLotSale());
        statistic.add(statisticService.QueryParkingLotSale());
        statistic.add(statisticService.QueryParkingLotAllOpen());
        statistic.add(statisticService.QueryParkingLotOpen());
        statistic.add(statisticService.QueryParkingLotAllIdentify());
        statistic.add(statisticService.QueryParkingLotIdentify());
        session.setAttribute("date",list);
        session.setAttribute("parkingSale",sales);
        session.setAttribute("statistic",statistic);

        return "count-list";
    }


     /**
       * @Author tang
       * @Date 2021/9/7
       * @Description: 用户统计功能
      */
    @RequestMapping("/usercount")
    public String usercount(HttpSession session){
        SimpleDateFormat sdf=new SimpleDateFormat("MM-dd");
        List<String> list =new ArrayList<>();
        List<Integer> sales =new ArrayList<>();
        List<Integer> visit =new ArrayList<>();
        List<Integer> Order=new ArrayList<>();
        list.clear();
        sales.clear();
        visit.clear();
        Order.clear();
        for(int i=0;i<7;i++){
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(new Date());
            calendar.add(calendar.DATE,-i);
            list.add(sdf.format(calendar.getTime()));
            String value1 =(String) redisTemplate.opsForHash().get("user", sdf.format(calendar.getTime()));
            String value2 =(String) redisTemplate.opsForHash().get("visit", sdf.format(calendar.getTime()));
            if(value1==null){
                sales.add(0);
            }else{
                sales.add(Integer.valueOf(value1));
            }
            if(value2==null){
                visit.add(0);
            }else{
                visit.add(Integer.valueOf(value2));
            }
        }

        Order.add(userOrderService.QueryAllOrderIndex());
        Order.add(userOrderService.QueryOrderByStateTwo(1));
        Order.add(userOrderService.QueryOrderByStateTwo(0));
        Order.add(userOrderService.QueryOrderByStateTwo(-1));
        session.setAttribute("ordervisit",Order);
        session.setAttribute("date",list);
        session.setAttribute("useradd",sales);
        session.setAttribute("visit",visit);
        return "usercount-list";
    }

     /**
       * @Author tang
       * @Date 2021/9/7
       * @Description: 业绩统计功能
      */
    @RequestMapping(value = "/datalist")
    public String datalist(HttpSession session){
        SimpleDateFormat sdf=new SimpleDateFormat("YY-MM-dd");
        List<String> visit =new ArrayList<>();
        List<Double> sales =new ArrayList<>();
        sales.clear();
        visit.clear();
        for(int i=0;i<7;i++){
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(new Date());
            calendar.add(calendar.DATE,-i);
            visit.add(sdf.format(calendar.getTime()));
            Double money = statisticService.QueryOrderMoney(sdf.format(calendar.getTime()));
            if(money==null){
                sales.add(0.0);
            }else {
                sales.add(money);
            }
        }
        System.out.println(visit);
        System.out.println(sales);
              session.setAttribute("date",visit);
        session.setAttribute("OrderMoney",sales);
        return "data-list";
    }
}
