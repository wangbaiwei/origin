package com.wbw.serviceorder.service;

import com.wbw.internalcommon.dto.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("service-driver-user")
public interface ServerDriverUserClient {


    @GetMapping("/city-driver/is-avaliable-driver")
    ResponseResult<Boolean> isAvaliableDriver(@RequestParam String cityCode);

}
