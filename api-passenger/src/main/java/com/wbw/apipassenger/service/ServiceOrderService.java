package com.wbw.apipassenger.service;

import com.wbw.apipassenger.remote.ServiceOrderClient;
import com.wbw.internalcommon.dto.ResponseResult;
import com.wbw.internalcommon.request.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceOrderService {


    @Autowired
    private ServiceOrderClient serviceOrderClient;

    public ResponseResult add(OrderRequest orderRequest) {
        return serviceOrderClient.add(orderRequest);
    }
}
