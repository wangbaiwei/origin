package com.wbw.apiboss.service;

import com.wbw.apiboss.remote.ServiceDriverUserClient;
import com.wbw.internalcommon.dto.Car;
import com.wbw.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CarService {


    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;

    public ResponseResult addCar(Car car) {
       return serviceDriverUserClient.addCar(car);
    }





}
