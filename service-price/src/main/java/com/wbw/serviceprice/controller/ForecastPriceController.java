package com.wbw.serviceprice.controller;


import com.wbw.internalcommon.dto.ResponseResult;
import com.wbw.internalcommon.request.ForecasePriceDTO;
import com.wbw.serviceprice.service.ForecastPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForecastPriceController {

    @Autowired
    private ForecastPriceService forecastPriceService;

    @PostMapping("forecast-price")
    public ResponseResult forecastPrice(@RequestBody ForecasePriceDTO forecasePriceDTO) {


        String depLongitude = forecasePriceDTO.getDepLongitude();
        String depLatitude = forecasePriceDTO.getDepLatitude();
        String desLongitude = forecasePriceDTO.getDesLongitude();
        String desLatitude = forecasePriceDTO.getDesLatitude();
        String cityCode = forecasePriceDTO.getCityCode();
        String vehicleType = forecasePriceDTO.getVehicleType();
        ResponseResult responseResult = forecastPriceService.forecastPrice(depLongitude, depLatitude, desLongitude, desLatitude, cityCode, vehicleType);

        return responseResult;
    }


}
