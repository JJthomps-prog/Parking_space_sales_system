package com.ykw.parking.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserParking {
    private String uid;
    private int pid;
    private int plid;
    private String city;
    private String address;
    private String number;
    private String name;
    private String id_number;
    private String phone_number;
}
