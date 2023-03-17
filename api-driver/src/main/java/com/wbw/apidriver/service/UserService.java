package com.wbw.apidriver.service;

import com.alibaba.nacos.common.model.RestResult;
import com.wbw.apidriver.remote.ServiceDriverUserClient;
import com.wbw.internalcommon.dto.DriverUser;
import com.wbw.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;


    public ResponseResult updateUser(DriverUser driverUser) {
        ResponseResult responseResult = serviceDriverUserClient.updateUser(driverUser);
        return responseResult;
    }
}
