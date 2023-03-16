package com.wbw.servicemap.controller;

import com.wbw.internalcommon.dto.ResponseResult;
import com.wbw.servicemap.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DistrictController {


    @Autowired
    DistrictService districtService;


    @GetMapping("/dic-district")
    public ResponseResult initDistrict(String keywords) {
        ResponseResult responseResult = districtService.initDicDistrict(keywords);
        return responseResult;
    }


}
