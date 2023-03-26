package com.wbw.servicemap.controller;

import com.wbw.internalcommon.dto.ResponseResult;
import com.wbw.internalcommon.response.TerminalResponse;
import com.wbw.servicemap.service.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(method = RequestMethod.POST, value = "/terminal")
public class TerminalController {


    @Autowired
    private TerminalService terminalService;

    @PostMapping("/add")
    public ResponseResult add(String name, String desc) {
        return terminalService.add(name, desc);
    }


    @PostMapping("/aroundsearch")
    public ResponseResult<List<TerminalResponse>> aroundSearch(String center, Integer radius) {
        return terminalService.aroundSearch(center, radius);
    }


}
