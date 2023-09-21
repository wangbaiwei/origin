package com.wbw.apipassenger.service;


import com.wbw.apipassenger.remote.ServicePriceClient;
import com.wbw.internalcommon.dto.ResponseResult;
import com.wbw.internalcommon.request.ForecasePriceDTO;
import com.wbw.internalcommon.response.ForecastPriceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ForeCastPriceService {

    @Autowired
    ServicePriceClient servicePriceClient;


    /**
     * 估算价格
     * @param depLongitude
     * @param depLatitude
     * @param desLongitude
     * @param desLatitude
     * @return
     */
    public ResponseResult forecastPrice(String depLongitude, String depLatitude, String desLongitude, String desLatitude, String cityCode, String vehicleType) {


        ForecasePriceDTO forecasePriceDTO = ForecasePriceDTO.builder()
                .depLongitude(depLongitude)
                .depLatitude(depLatitude)
                .desLongitude(desLongitude)
                .desLatitude(desLatitude)
                .cityCode(cityCode)
                .vehicleType(vehicleType)
                .build();

        ResponseResult<ForecastPriceResponse> forecast = servicePriceClient.forecast(forecasePriceDTO);
        return ResponseResult.success(forecast.getData());

    }


}
