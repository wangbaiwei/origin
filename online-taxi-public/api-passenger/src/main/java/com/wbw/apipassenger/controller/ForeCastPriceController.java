package com.wbw.apipassenger.controller;


import com.wbw.apipassenger.service.ForeCastPriceService;
import com.wbw.internalcommon.dto.ResponseResult;
import com.wbw.internalcommon.request.ForecasePriceDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ForeCastPriceController {

    @Autowired
    private ForeCastPriceService foreCastPriceService;

    @PostMapping("/forecast-price")
    public ResponseResult forecastPrice(@RequestBody ForecasePriceDTO forecasePriceDTO) {

        String depLongitude = forecasePriceDTO.getDepLongitude();
        String depLatitude = forecasePriceDTO.getDepLatitude();
        String desLongitude = forecasePriceDTO.getDesLongitude();
        String desLatitude = forecasePriceDTO.getDesLatitude();

        return foreCastPriceService.forecastPrice(depLongitude, depLatitude, desLongitude, desLatitude);

    }
}
