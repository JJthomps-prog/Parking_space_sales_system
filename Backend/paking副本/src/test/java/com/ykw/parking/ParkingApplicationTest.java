package com.ykw.parking;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;


@SpringBootTest()
class ParkingApplicationTest {

    @Test
    void contextLoads() {
        DefaultProfile profile=DefaultProfile.getProfile("cn-hangzhou",
                "LTAI5tSqz3RvxXmnU3AMJz13",
                "wFSzLGO88www0GuvW0DVDu3X6bpvYW");
        IAcsClient client=new DefaultAcsClient(profile);
        CommonRequest request=new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");//不要动
        request.setVersion("2017-05-25");//不要动
        request.setAction("SendSms");//不用动
        //自定义参数(手机号，验证码，签名，模板）
        request.putQueryParameter("PhoneNumbers","13765683760");
        request.putQueryParameter("SignName","栓马桩");
        request.putQueryParameter("TemplateCode","SMS_223588900");

        HashMap<String, Object> HashMap = new HashMap<>();
        HashMap.put("code",2333);
        request.putQueryParameter("TemplateCode", JSONObject.toJSONString(HashMap));

        try{
            CommonResponse response =client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }

    }


}
