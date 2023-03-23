package com.wbw.servicemap.service;

import com.wbw.internalcommon.dto.ResponseResult;
import com.wbw.servicemap.remote.TrackClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrackService {

    @Autowired
    private TrackClient trackClient;

    public ResponseResult add(String tid) {
        return trackClient.add(tid);
    }
}
