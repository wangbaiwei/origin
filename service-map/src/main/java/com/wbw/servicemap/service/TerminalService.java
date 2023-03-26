package com.wbw.servicemap.service;

import com.wbw.internalcommon.dto.ResponseResult;
import com.wbw.internalcommon.response.TerminalResponse;
import com.wbw.servicemap.remote.TerminalClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TerminalService {


    @Autowired
    private TerminalClient terminalClient;

    public ResponseResult add(String name, String desc) {
        return terminalClient.add(name, desc);
    }


    public ResponseResult<List<TerminalResponse>> aroundSearch(String center, Integer radius) {
        return terminalClient.aroundSearch(center, radius);
    }
}
