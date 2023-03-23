package com.wbw.servicemap.controller;


import com.wbw.internalcommon.dto.ResponseResult;
import com.wbw.internalcommon.request.PointRequest;
import com.wbw.servicemap.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(method = RequestMethod.POST, value = "/point")
public class PointController {

    @Autowired
    private PointService pointService;

    @PostMapping("/upload")
    public ResponseResult upload(@RequestBody PointRequest pointRequest) {
        return pointService.upload(pointRequest);
    }
}
