package com.wbw.servcedirveruser.service;

import com.wbw.internalcommon.dto.DriverUser;
import com.wbw.internalcommon.dto.DriverUserWorkStatus;
import com.wbw.internalcommon.dto.ResponseResult;
import com.wbw.servcedirveruser.mapper.DriverUserMapper;
import com.wbw.servcedirveruser.mapper.DriverUserWorkStatusMapper;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 王佰伟
 * @since 2023-03-19
 */
@Service
public class DriverUserWorkStatusService {

    @Autowired
    private DriverUserWorkStatusMapper driverUserWorkStatusMapper;

    public ResponseResult changeWorkStatus(Long driverId, Integer workStatus) {

        Map<String, Object> map = new HashMap<>();
        map.put("driver_id", driverId);
        List<DriverUserWorkStatus> driverUserWorkStatuses = driverUserWorkStatusMapper.selectByMap(map);

        DriverUserWorkStatus driverUserWorkStatus = driverUserWorkStatuses.get(0);
        LocalDateTime now = LocalDateTime.now();
        driverUserWorkStatus.setWorkStatus(workStatus);
        driverUserWorkStatus.setGmtCreate(now);
        driverUserWorkStatus.setGmtModified(now);

        driverUserWorkStatusMapper.updateById(driverUserWorkStatus);
        return ResponseResult.success("");
    }

}
