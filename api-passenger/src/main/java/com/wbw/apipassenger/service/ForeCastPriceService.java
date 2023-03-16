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
    public ResponseResult forecastPrice(String depLongitude, String depLatitude, String desLongitude, String desLatitude) {

        log.info("出发地的经度：{}", depLongitude);
        log.info("出发地的维度：{}", depLatitude);
        log.info("目的地的经度：{}", desLongitude);
        log.info("目的地的维度：{}", desLatitude);

        ForecasePriceDTO forecasePriceDTO = ForecasePriceDTO.builder()
                .depLongitude(depLongitude).depLatitude(depLatitude)
                .desLongitude(desLongitude).desLatitude(desLatitude).build();
        ResponseResult<ForecastPriceResponse> forecast = servicePriceClient.forecast(forecasePriceDTO);
        double price = forecast.getData().getPrice();

        log.info("调用计价服务，计算价格");
        ForecastPriceResponse forecastPriceResponse = new ForecastPriceResponse();
        forecastPriceResponse.setPrice(price);
        return ResponseResult.success(forecastPriceResponse);

    }


}
