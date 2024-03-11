package com.ts.parking.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author: tang
 * @Date: 2021/10/25/17:26
 * @Description:
 */
@Data
@TableName("bank")
public class Bank {
    private  String card;
    private String user;
    private Double money;
}
