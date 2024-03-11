package com.ts.parking.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("parking_one")
public class Parking {
    private Integer id;
    private String city;
    private String address;
    private String owner_phone;
    private String owner_name;
    private int number;

}
