package com.ts.parking.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("shoppingcart")
public class ShoppingCart {
    private String parkingid;
    private String userid;
}
