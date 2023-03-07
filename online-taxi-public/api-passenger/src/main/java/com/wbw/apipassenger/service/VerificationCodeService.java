package com.wbw.apipassenger.service;

import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class VerificationCodeService {

    public String generatorCode(String passengerPhone) {

        // 调用验证码服务，获取验证码
        System.out.println("调用验证码服务，获取验证码");
        String code = "111111";

        // 存入到redis中
        System.out.println("存入redis");

        // 返回值
        Gson gson = new Gson();
        Map<String, String> returnMessage = new HashMap<>();
        returnMessage.put("code", "1");
        returnMessage.put("message", "success");
        String s = gson.toJson(returnMessage);

        return s;


    }
}
