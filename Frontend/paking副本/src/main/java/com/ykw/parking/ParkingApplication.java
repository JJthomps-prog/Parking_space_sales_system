package com.ykw.parking;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication()
@EnableScheduling //开启基于注解的定时任务
@MapperScan("com.ykw.parking.mapper")
public class ParkingApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(ParkingApplication.class, args);

        //2、查看容器里面的组件
/*        String[] names = run.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);

        }*/

    }

}
