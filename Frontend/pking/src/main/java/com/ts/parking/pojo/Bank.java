package com.ts.parking.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("bank")
public class Bank {
    private  String card;
    private String user;
    private Double money;
}
