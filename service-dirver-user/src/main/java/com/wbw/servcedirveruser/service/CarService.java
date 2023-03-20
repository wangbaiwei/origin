package com.wbw.servcedirveruser.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wbw.internalcommon.dto.Car;
import com.wbw.internalcommon.dto.ResponseResult;
import com.wbw.internalcommon.response.TerminalResponse;
import com.wbw.servcedirveruser.mapper.CarMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wbw.servcedirveruser.remote.ServiceMapClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 王佰伟
 * @since 2023-03-17
 */
@Service
public class CarService extends ServiceImpl<CarMapper, Car> implements IService<Car> {

    @Autowired
    private CarMapper carMapper;

    @Autowired
    private ServiceMapClient serviceMapClient;

    public ResponseResult addCar(Car car) {

        // 获得车辆的tid
        ResponseResult<TerminalResponse> responseResult = serviceMapClient.addTerminal(car.getVehicleNo());


        LocalDateTime now = LocalDateTime.now();

        car.setGmtModified(now);
        car.setGmtCreate(now);
        car.setTid(responseResult.getData().getTid());
        int insert = carMapper.insert(car);
        return ResponseResult.success(insert);
    }

}
