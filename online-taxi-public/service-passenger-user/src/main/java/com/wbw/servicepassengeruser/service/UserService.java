package com.wbw.servicepassengeruser.service;

import com.wbw.internalcommon.dto.ResponseResult;
import com.wbw.internalcommon.request.VerificationCodeDTD;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserService {


    public ResponseResult loginOrRegister(String passengerPhone) {
        // 根据手机号查询用户信息

        // 判断用户信息是否存在
        // 若不存在，插入用户信息

        return null;

    }

}
