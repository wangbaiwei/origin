package com.wbw.serviceorder.remote;

import com.wbw.internalcommon.dto.PriceRule;
import com.wbw.internalcommon.dto.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("service-price")
public interface ServicePriceClient {

    @RequestMapping(method = RequestMethod.GET, value = "/price-rule/if-exists")
    ResponseResult<Boolean> ifPriceExists(@RequestBody PriceRule priceRule);

}
