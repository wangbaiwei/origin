package com.wbw.serviceprice.service;

import com.wbw.internalcommon.dto.ResponseResult;
import com.wbw.internalcommon.request.ForecasePriceDTO;
import com.wbw.internalcommon.response.DirectionResponse;
import com.wbw.internalcommon.response.ForecastPriceResponse;
import com.wbw.serviceprice.remote.ServiceMapClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ForecastPriceService {

    @Autowired
    private ServiceMapClient serviceMapClient;


    /**
     * 估算价格
     * @param depLongitude
     * @param depLatitude
     * @param desLongitude
     * @param desLatitude
     * @return
     */
    public ResponseResult forecastPrice(String depLongitude, String depLatitude, String desLongitude, String desLatitude) {

        log.info("出发地的经度：{}, 出发地的维度：{}, 目的地的经度：{}, 目的地的维度：{}", depLongitude, depLatitude, desLongitude, desLatitude);
        ForecasePriceDTO forecasePriceDTO = new ForecasePriceDTO();
        forecasePriceDTO.setDepLongitude(depLongitude);
        forecasePriceDTO.setDepLatitude(depLatitude);
        forecasePriceDTO.setDesLongitude(desLongitude);
        forecasePriceDTO.setDesLongitude(desLongitude);

        log.info("调用地图服务，查询距离和时长");
        ResponseResult<DirectionResponse> direction = serviceMapClient.direction(forecasePriceDTO);
        Integer distance = direction.getData().getDistance();
        Integer duration = direction.getData().getDuration();
        log.info("距离：{}, 时长：{}", distance, duration);


        log.info("读取计价规则");
        log.info("根据距离，时长和计价规则，计算价格");
        ForecastPriceResponse forecastPriceResponse = new ForecastPriceResponse();
        forecastPriceResponse.setPrice(12.34);
        return ResponseResult.success(forecastPriceResponse);

    }
}
