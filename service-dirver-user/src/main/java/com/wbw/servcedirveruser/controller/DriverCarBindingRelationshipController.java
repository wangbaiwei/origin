package com.wbw.servcedirveruser.controller;


import com.wbw.internalcommon.dto.DriverCarBindingRelationship;
import com.wbw.internalcommon.dto.ResponseResult;
import com.wbw.servcedirveruser.service.DriverCarBindingRelationshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 王佰伟
 * @since 2023-03-18
 */
@RestController
@RequestMapping(method = RequestMethod.POST, value = "/driver-car-binding-relationship")
public class DriverCarBindingRelationshipController {

    @Autowired
    private DriverCarBindingRelationshipService driverCarBindingRelationshipService;

    @PostMapping("/bind")
    public ResponseResult bind(@RequestBody DriverCarBindingRelationship driverCarBindingRelationship) {

        return driverCarBindingRelationshipService.bind(driverCarBindingRelationship);

    }

}
