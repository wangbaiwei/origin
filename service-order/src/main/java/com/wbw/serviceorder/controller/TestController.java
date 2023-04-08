package com.wbw.serviceorder.controller;


import com.wbw.internalcommon.dto.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public ResponseResult test() {
        return ResponseResult.success("service-order");
    }
}
