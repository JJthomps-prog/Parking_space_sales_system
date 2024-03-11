package com.ykw.parking.uitl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: tang
 * @Date: 2021/09/09/21:27
 * @Description: 短信业务
 */
public class SendSms {
    public static boolean send(String PhoneNumber, String TemplateCode, Map<String,Object> code){
        //连接阿里云
        DefaultProfile profile=DefaultProfile.getProfile("cn-hangzhou",
                "LTAI5tSqz3RvxXmnU3AMJz13",
                "wFSzLGO88www0GuvW0DVDu3X6bpvYW");
        IAcsClient client=new DefaultAcsClient(profile);

        //构建请求
        CommonRequest request=new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");//不要动
        request.setVersion("2017-05-25");//不要动
        request.setAction("SendSms");//不用动
        //自定义参数(手机号，验证码，签名，模板）
        request.putQueryParameter("PhoneNumbers",PhoneNumber);
        request.putQueryParameter("SignName","栓马桩");
        request.putQueryParameter("TemplateCode",TemplateCode);//SMS_223588900
        request.putQueryParameter("TemplateCode", JSONObject.toJSONString(code));
        try{
            CommonResponse response =client.getCommonResponse(request);
            System.out.println(response.getData());
            return response.getHttpResponse().isSuccess();
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;

    }
}
