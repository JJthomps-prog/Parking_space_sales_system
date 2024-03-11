package com.ts.parking.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
    private String id;
    private String admin_name;
    private String admin_password;
    private Integer role;
}
