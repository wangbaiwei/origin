package com.wbw.apidriver.remote;

import com.wbw.internalcommon.dto.ResponseResult;
import com.wbw.internalcommon.request.PointRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("service-map")
public interface ServiceMapClient {

    @RequestMapping(method = RequestMethod.POST, value = "/point/upload")
    ResponseResult upload(@RequestBody PointRequest pointRequest);

}
