package com.wbw.servcedirveruser.service;

import com.wbw.internalcommon.dto.DriverUser;
import com.wbw.internalcommon.dto.ResponseResult;
import com.wbw.servcedirveruser.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    @Autowired
    private UserMapper driverUserMapper;


    public ResponseResult testGetDirverUser() {
        DriverUser driverUser = driverUserMapper.selectById("1");
        return ResponseResult.success(driverUser);
    }


    public ResponseResult addDriverUser(DriverUser driverUser) {
        LocalDateTime now = LocalDateTime.now();
        driverUser.setGmtCreate(now);
        driverUser.setGmtModified(now);
        int insert = driverUserMapper.insert(driverUser);
        if (insert > 0){
            return ResponseResult.success(insert);
        }

        return ResponseResult.fail(0, "失败");
    }


    public ResponseResult updateDriverUser(DriverUser driverUser) {
        LocalDateTime now = LocalDateTime.now();
        driverUser.setGmtCreate(now);
        driverUser.setGmtModified(now);
        int insert = driverUserMapper.updateById(driverUser);
        return ResponseResult.success(insert);

    }


}
