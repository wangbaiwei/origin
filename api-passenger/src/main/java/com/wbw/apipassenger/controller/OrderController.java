package com.wbw.apipassenger.controller;


import com.wbw.apipassenger.service.ServiceOrderService;
import com.wbw.internalcommon.dto.ResponseResult;
import com.wbw.internalcommon.request.OrderRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(method = RequestMethod.POST, value = "/order")
public class OrderController {


    @Autowired
    private ServiceOrderService serviceOrderService;

    @PostMapping("/add")
    public ResponseResult add(@RequestBody OrderRequest orderRequest){
        log.info(orderRequest.toString());
        return serviceOrderService.add(orderRequest);
    }
}
