package com.wbw.serviceprice.service;

import com.wbw.internalcommon.dto.ResponseResult;
import com.wbw.serviceprice.response.ForecastPriceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ForecastPriceService {


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



        log.info("调用地图服务，查询距离和时长");
        log.info("读取计价规则");
        log.info("根据距离，时长和计价规则，计算价格");
        ForecastPriceResponse forecastPriceResponse = new ForecastPriceResponse();
        forecastPriceResponse.setPrice(12.34);
        return ResponseResult.success(forecastPriceResponse);

    }
}
