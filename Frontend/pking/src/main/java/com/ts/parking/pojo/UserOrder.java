package com.ts.parking.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOrder {
    private String oid;
    private String uid;
    private Date date;
    private int pid;
    private String number;
    private String city;
    private String address;
    private int state;
    private double price;
    private int plid;
}
