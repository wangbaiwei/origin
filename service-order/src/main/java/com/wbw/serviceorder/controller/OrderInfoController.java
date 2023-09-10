package com.wbw.serviceorder.controller;


import com.wbw.internalcommon.constant.HeaderParamConstants;
import com.wbw.internalcommon.dto.ResponseResult;
import com.wbw.internalcommon.request.OrderRequest;
import com.wbw.serviceorder.service.OrderInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 王佰伟
 * @since 2023-04-08
 */
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderInfoController {

    @Autowired
    private OrderInfoService orderInfoService;


    @PostMapping("/add")
    public ResponseResult add(@RequestBody OrderRequest orderRequest, HttpServletRequest request) {

        log.info(orderRequest.toString());
        String deviceCode = request.getHeader(HeaderParamConstants.DEVICE_CODE);
        orderRequest.setDeviceCode(deviceCode);
        return orderInfoService.add(orderRequest);
    }

}
