package com.wbw.serviceorder.controller;

import com.wbw.internalcommon.dto.ResponseResult;
import com.wbw.internalcommon.request.OrderRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @PostMapping("/add")
    public ResponseResult add(@RequestBody OrderRequest orderRequest) {
        log.info(orderRequest.toString());
        return ResponseResult.success(orderRequest);
    }
}
