package com.wbw.servicemap.controller;


import com.wbw.internalcommon.dto.ResponseResult;
import com.wbw.internalcommon.request.ForecasePriceDTO;
import com.wbw.servicemap.service.DirectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/direction")
public class DirectionController {

    @Autowired
    private DirectionService directionService;


    @GetMapping("/driving")
    public ResponseResult dirving(@RequestBody ForecasePriceDTO forecasePriceDTO) {
        String depLongitude = forecasePriceDTO.getDepLongitude();
        String depLatitude = forecasePriceDTO.getDepLatitude();
        String desLongitude = forecasePriceDTO.getDesLongitude();
        String desLatitude = forecasePriceDTO.getDesLatitude();

        ResponseResult driving = directionService.driving(depLongitude, depLatitude, desLongitude, desLatitude);
        return driving;

    }

}
