package com.wbw.apipassenger.service;

import com.google.gson.Gson;
import com.wbw.apipassenger.remote.ServiceVerificationcodeClient;
import com.wbw.internalcommon.dto.ResponseResult;
import com.wbw.internalcommon.response.NumberCodeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class VerificationCodeService {

    @Autowired
    private ServiceVerificationcodeClient serviceVerificationcodeClient;

    public String generatorCode(String passengerPhone) {

        // 调用验证码服务，获取验证码
        System.out.println("调用验证码服务，获取验证码");
        ResponseResult<NumberCodeResponse> numberCodeResponse = serviceVerificationcodeClient.getNumberCode(10);
        int numberCode = numberCodeResponse.getData().getNumberCode();
        System.out.println("remote number code: " + numberCode);

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
