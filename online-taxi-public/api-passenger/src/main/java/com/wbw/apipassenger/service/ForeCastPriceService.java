package com.wbw.apipassenger.service;


import com.wbw.apipassenger.response.ForecastPriceResponse;
import com.wbw.internalcommon.dto.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ForeCastPriceService {


    /**
     * 估算价格
     * @param depLongitude
     * @param depLatitude
     * @param desLongitude
     * @param desLatitude
     * @return
     */
    public ResponseResult forecastPrice(String depLongitude, String depLatitude, String desLongitude, String desLatitude) {

        log.info("出发地的经度：{}", depLongitude);
        log.info("出发地的维度：{}", depLatitude);
        log.info("目的地的经度：{}", desLongitude);
        log.info("目的地的维度：{}", desLatitude);


        log.info("调用计价服务，计算价格");

        ForecastPriceResponse forecastPriceResponse = new ForecastPriceResponse();
        forecastPriceResponse.setPrice(12.34);
        return ResponseResult.success(forecastPriceResponse);

    }


}
