package com.ts.parking.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("renchou")
public class RenChou {
    private String parkingLot_id;
    private String user_id;
}
