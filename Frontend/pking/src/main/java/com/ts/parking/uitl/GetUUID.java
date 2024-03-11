package com.ts.parking.uitl;

import java.util.UUID;

 /**
   * @Description: 生成用户ID
  */
public class GetUUID {
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        String uuidStr = str.replace("-", "");
        return uuidStr;
    }
}
