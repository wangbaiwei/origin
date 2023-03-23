package com.wbw.apidriver.service;

import com.wbw.apidriver.remote.ServiceDriverUserClient;
import com.wbw.apidriver.remote.ServiceMapClient;
import com.wbw.internalcommon.dto.Car;
import com.wbw.internalcommon.dto.ResponseResult;
import com.wbw.internalcommon.request.ApiDriverPointRequest;
import com.wbw.internalcommon.request.PointRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PointService {

    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;

    @Autowired
    private ServiceMapClient serviceMapClient;

    public ResponseResult upload(ApiDriverPointRequest apiDriverPointRequest) {
        // 获取carId

        Long carId = apiDriverPointRequest.getCarId();

        // 通过carId获取tid， trid，调用service-driver-user的接口

        ResponseResult<Car> carById = serviceDriverUserClient.getBarById(carId);
        Car car = carById.getData();
        String tid = car.getTid();
        String trid = car.getTrid();
        log.info("tid:{}, trid:{}", tid, trid);

        PointRequest pointRequest = new PointRequest();
        pointRequest.setTrid(trid);
        pointRequest.setTid(tid);
        pointRequest.setPoints(apiDriverPointRequest.getPoints());

        // 上传轨迹点
        ResponseResult upload = serviceMapClient.upload(pointRequest);

        return upload;
    }
}
