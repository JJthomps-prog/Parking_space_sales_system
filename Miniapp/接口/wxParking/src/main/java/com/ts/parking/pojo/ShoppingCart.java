package com.ts.parking.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author: tang
 * @Date: 2021/10/23/15:18
 * @Description:
 */
@Data
@TableName("shoppingcart")
public class ShoppingCart {
    private String parkingid;
    private String userid;
}
