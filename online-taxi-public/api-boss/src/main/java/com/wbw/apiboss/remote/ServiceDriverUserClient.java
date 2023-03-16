package com.wbw.apiboss.remote;


import com.wbw.internalcommon.dto.DriverUser;
import com.wbw.internalcommon.dto.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("service-dirver-user")
public interface ServiceDriverUserClient {

    @RequestMapping(method = RequestMethod.POST, value = "/user")
    ResponseResult addDriverUser(@RequestBody DriverUser driverUser);


    @RequestMapping(method = RequestMethod.PUT, value = "/user")
    ResponseResult updateDriverUser(@RequestBody DriverUser driverUser);
}
