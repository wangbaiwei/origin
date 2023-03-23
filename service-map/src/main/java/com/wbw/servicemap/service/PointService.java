package com.wbw.servicemap.service;

import com.wbw.internalcommon.dto.ResponseResult;
import com.wbw.internalcommon.request.PointRequest;
import com.wbw.servicemap.remote.PointClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PointService {

    @Autowired
    private PointClient pointClient;


    public ResponseResult upload(@RequestBody PointRequest pointRequest) {

        return pointClient.upload(pointRequest);
    }
}
