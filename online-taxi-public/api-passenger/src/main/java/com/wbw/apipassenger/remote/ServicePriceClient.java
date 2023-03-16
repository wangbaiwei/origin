package com.wbw.apipassenger.remote;


import com.wbw.internalcommon.dto.ResponseResult;
import com.wbw.internalcommon.request.ForecasePriceDTO;
import com.wbw.internalcommon.response.ForecastPriceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("service-price")
public interface ServicePriceClient {
    @RequestMapping(method = RequestMethod.POST, value = "/forecast-price")
    ResponseResult<ForecastPriceResponse> forecast(@RequestBody ForecasePriceDTO forecasePriceDTO);
}
