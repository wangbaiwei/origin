package com.wbw.servicemap.service;


import com.wbw.internalcommon.dto.ResponseResult;
import com.wbw.servicemap.remote.ServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceFromMapService {

    @Autowired
    private ServiceClient serviceClient;

    /**
     * 创建服务
     * @param name
     * @return
     */
    public ResponseResult add(String name) {

        ResponseResult add = serviceClient.add(name);
        return add;

    }
}
