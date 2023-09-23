package com.wbw.servcedirveruser.service;

import com.wbw.internalcommon.dto.ResponseResult;
import com.wbw.servcedirveruser.mapper.DriverUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityDriverUserService {

    @Autowired
    DriverUserMapper driverUserMapper;

    public ResponseResult<Boolean> isAvailableDriver(String cityCode) {
        int res = driverUserMapper.selectDriverUserCountByCityCode(cityCode);
        if (res > 0) {
            return ResponseResult.success(true);
        } else {
            return ResponseResult.fail(false);
        }
    }
}
