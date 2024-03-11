package com.ts.parking.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: tang
 * @Date: 2021/10/20/20:24
 * @Description:
 */
@Data
public class ParkingVo {
    private Integer id;
    private Integer pid;
    private Double price;
    private String address;
    private Integer open;
    private String owner_phone;
    private String owner_name;
}
