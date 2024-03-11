package com.ts.parking.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoan {
    private String id;
    private String name;
    private String address;
    private String contact;
    private String company;
    private Double money;
    private Double interest;
    private Date date;
    private String uid;

}
