package com.wbw.servcedirveruser.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wbw.internalcommon.constant.DriverCarConstants;
import com.wbw.internalcommon.dto.DriverCarBindingRelationship;
import com.wbw.internalcommon.dto.ResponseResult;
import com.wbw.servcedirveruser.mapper.DriverCarBindingRelationshipMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 王佰伟
 * @since 2023-03-18
 */
@Service
public class DriverCarBindingRelationshipService extends ServiceImpl<DriverCarBindingRelationshipMapper, DriverCarBindingRelationship> implements IService<DriverCarBindingRelationship> {

    @Autowired
    private DriverCarBindingRelationshipMapper driverCarBindingRelationshipMapper;


    public ResponseResult bind(DriverCarBindingRelationship driverCarBindingRelationship) {
        LocalDateTime now = LocalDateTime.now();

        driverCarBindingRelationship.setBindingTime(now);
        driverCarBindingRelationship.setBindState(DriverCarConstants.DRIVER_CAR_BIND); // 1-绑定 2-解绑

        driverCarBindingRelationshipMapper.insert(driverCarBindingRelationship);
        return ResponseResult.success();
    }
}
