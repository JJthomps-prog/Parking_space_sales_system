package com.ykw.parking.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Parking {
    private Integer id;
    private String city;
    private String address;
    private String owner_phone;
    private String owner_name;
    private int number;

}
