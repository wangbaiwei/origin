package com.wbw.servcedirveruser.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wbw.internalcommon.constant.CommonStatusEnum;
import com.wbw.internalcommon.constant.DriverCarConstants;
import com.wbw.internalcommon.dto.DriverCarBindingRelationship;
import com.wbw.internalcommon.dto.DriverUser;
import com.wbw.internalcommon.dto.DriverUserWorkStatus;
import com.wbw.internalcommon.dto.ResponseResult;
import com.wbw.internalcommon.response.OrderDriverResponse;
import com.wbw.servcedirveruser.mapper.DriverCarBindingRelationshipMapper;
import com.wbw.servcedirveruser.mapper.DriverUserMapper;
import com.wbw.servcedirveruser.mapper.DriverUserWorkStatusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DriverUserService {

    @Autowired
    private DriverUserMapper driverUserMapper;

    @Autowired
    private DriverUserWorkStatusMapper driverUserWorkStatusMapper;

    public ResponseResult testGetDirverUser() {
        DriverUser driverUser = driverUserMapper.selectById("1");
        return ResponseResult.success(driverUser);
    }


    public ResponseResult addDriverUser(DriverUser driverUser) {
        LocalDateTime now = LocalDateTime.now();
        driverUser.setGmtCreate(now);
        driverUser.setGmtModified(now);
        driverUserMapper.insert(driverUser);

        // 初始化工作状态表
        DriverUserWorkStatus driverUserWorkStatus = new DriverUserWorkStatus();
        driverUserWorkStatus.setDriverId(DriverCarConstants.DRIVER_WORK_STATUS_STOP);
        driverUserWorkStatus.setGmtCreate(now);
        driverUserWorkStatus.setGmtModified(now);
        driverUserWorkStatusMapper.insert(driverUserWorkStatus);

        return ResponseResult.success("");
    }


    public ResponseResult updateDriverUser(DriverUser driverUser) {
        LocalDateTime now = LocalDateTime.now();
        driverUser.setGmtCreate(now);
        driverUser.setGmtModified(now);
        int insert = driverUserMapper.updateById(driverUser);
        return ResponseResult.success(insert);

    }

    public ResponseResult getDriverUserByPhone(String driverPhone) {


        Map<String, Object> map = new HashMap<>();
        map.put("driver_phone", driverPhone);
        map.put("state", DriverCarConstants.DRIVER_STATE_VALID);
        List<DriverUser> driverUsers = driverUserMapper.selectByMap(map);

        if (driverUsers.isEmpty()) {
            return ResponseResult.fail(CommonStatusEnum.DRIVER_NOT_EXISTS.getCode(), CommonStatusEnum.DRIVER_NOT_EXISTS.getValue());
        }

        return ResponseResult.success(driverUsers.get(0));
    }

    @Autowired
    DriverCarBindingRelationshipMapper driverCarBindingRelationshipMapper;

    public ResponseResult<OrderDriverResponse> getAvaliableDriver(@PathVariable("carId") Long carId) {
        LambdaQueryWrapper<DriverCarBindingRelationship> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DriverCarBindingRelationship::getCarId, carId);
        queryWrapper.eq(DriverCarBindingRelationship::getBindState, DriverCarConstants.DRIVER_CAR_BIND);

        // 查询司机和车辆绑定关系
        DriverCarBindingRelationship relationship = driverCarBindingRelationshipMapper.selectOne(queryWrapper);
        if (relationship == null) {
            return ResponseResult.fail(CommonStatusEnum.AVALIABLE_DRIVER_EMPTY);
        }

        Long driverId = relationship.getDriverId();

        // 查询司机的工作状态
        LambdaQueryWrapper<DriverUserWorkStatus> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(DriverUserWorkStatus::getDriverId, driverId);
        queryWrapper1.eq(DriverUserWorkStatus::getWorkStatus, DriverCarConstants.DRIVER_WORK_STATUS_START);
        DriverUserWorkStatus driverUserWorkStatus = driverUserWorkStatusMapper.selectOne(queryWrapper1);
        if (driverUserWorkStatus == null) {
            return ResponseResult.fail(CommonStatusEnum.AVALIABLE_DRIVER_EMPTY);
        } else {

            DriverUser driverUser = driverUserMapper.selectById(driverId);
            OrderDriverResponse response = new OrderDriverResponse();

            response.setCarId(carId);
            response.setDriverId(driverId);
            response.setDriverPhone(driverUser.getDriverPhone());
            return ResponseResult.success(response);
        }
    }

}
