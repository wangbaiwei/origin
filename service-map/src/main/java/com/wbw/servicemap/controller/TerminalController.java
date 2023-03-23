package com.wbw.servicemap.controller;

import com.wbw.internalcommon.dto.ResponseResult;
import com.wbw.servicemap.service.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(method = RequestMethod.POST, value = "/terminal")
public class TerminalController {


    @Autowired
    private TerminalService terminalService;

    @PostMapping("/add")
    public ResponseResult add(String name, String desc) {
        return terminalService.add(name, desc);
    }





}
