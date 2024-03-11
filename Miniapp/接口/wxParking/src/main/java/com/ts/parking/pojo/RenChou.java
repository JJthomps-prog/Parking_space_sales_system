package com.ts.parking.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author: tang
 * @Date: 2021/10/23/15:10
 * @Description:
 */
@Data
@TableName("renchou")
public class RenChou {
    private String parkingLot_id;
    private String user_id;
}
