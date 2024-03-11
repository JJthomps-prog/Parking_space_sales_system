package com.ts.parking.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: tang
 * @Date: 2021/10/25/15:40
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartVo {

    private Integer goods_id;
    private Integer checked=0;
    private String address;
    private String  number;
    private Double price;
}
