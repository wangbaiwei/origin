package com.wbw.apipassenger.remote;

import com.wbw.internalcommon.dto.ResponseResult;
import com.wbw.internalcommon.request.OrderRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("service-order")
public interface ServiceOrderClient {

    @RequestMapping(method = RequestMethod.POST, value = "/order/add")
    ResponseResult add(@RequestBody OrderRequest orderRequest);
}
