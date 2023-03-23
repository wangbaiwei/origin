package com.wbw.apidriver.controller;


import com.wbw.apidriver.service.PointService;
import com.wbw.internalcommon.dto.ResponseResult;
import com.wbw.internalcommon.request.ApiDriverPointRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("point")
public class PointController {

    @Autowired
    private PointService pointService;

    @PostMapping("/upload")
    public ResponseResult upload(@RequestBody ApiDriverPointRequest apiDriverPointRequest) {
        return pointService.upload(apiDriverPointRequest);
    }
}
