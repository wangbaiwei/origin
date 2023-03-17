package com.wbw.servcedirveruser.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wbw.internalcommon.dto.Car;
import com.wbw.internalcommon.dto.ResponseResult;
import com.wbw.servcedirveruser.mapper.CarMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

    public ResponseResult addCar(Car car) {

        LocalDateTime now = LocalDateTime.now();

        car.setGmtModified(now);
        car.setGmtCreate(now);
        int insert = carMapper.insert(car);
        return ResponseResult.success(insert);
    }

}
