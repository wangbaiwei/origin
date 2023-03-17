package com.wbw.servcedirveruser.controller;


import com.wbw.internalcommon.dto.Car;
import com.wbw.internalcommon.dto.ResponseResult;
import com.wbw.servcedirveruser.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 王佰伟
 * @since 2023-03-17
 */
@RestController
public class CarController {

    @Autowired
    private CarService carService;

    @PostMapping("/car")
    public ResponseResult addCar(@RequestBody Car car){

        ResponseResult responseResult = carService.addCar(car);
        return responseResult;
    }

}
