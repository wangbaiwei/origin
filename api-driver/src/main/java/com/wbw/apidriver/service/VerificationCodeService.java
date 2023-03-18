package com.wbw.apidriver.service;

import com.wbw.apidriver.remote.ServiceDriverUserClient;
import com.wbw.internalcommon.constant.CommonStatusEnum;
import com.wbw.internalcommon.constant.DriverCarConstants;
import com.wbw.internalcommon.dto.ResponseResult;
import com.wbw.internalcommon.response.DriverUserExistsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class VerificationCodeService {

    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;

    public ResponseResult checkAndSendVerificationCode(String driverPhone) {

        // 查询service-driver-user，该手机号的司机是否存在
        ResponseResult<DriverUserExistsResponse> driverUserExistsResponseResponseResult = serviceDriverUserClient.checkDriver(driverPhone);
        DriverUserExistsResponse data = driverUserExistsResponseResponseResult.getData();
        int isExists = data.getIsExists();
        if (isExists == DriverCarConstants.DRIVER_NOT_EXISTS) {
            log.info("{}对应的司机不存在", driverPhone);
            return ResponseResult.fail(CommonStatusEnum.DRIVER_NOT_EXISTS.getCode(), CommonStatusEnum.DRIVER_NOT_EXISTS.getValue());
        }
        log.info("{}对应的司机存在", driverPhone);


        // 获取验证码

        // 调用第三方发生验证码

        // 存入redis

        return ResponseResult.success(data);
    }
}
