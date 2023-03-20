package com.wbw.servicemap.controller;

import com.wbw.internalcommon.dto.ResponseResult;
import com.wbw.servicemap.service.ServiceFromMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(method = RequestMethod.POST, value = "/service")
public class ServiceController {

    @Autowired
    private ServiceFromMapService serviceFromMapService;

    /**
     * 创建服务
     * @param name
     * @return
     */
    @PostMapping("add")
    public ResponseResult add(String name) {
        return serviceFromMapService.add(name);
    }
}
