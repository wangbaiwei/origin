package com.wbw.servicemap.controller;

import com.wbw.internalcommon.dto.DicDistrict;
import com.wbw.servicemap.mapper.DicDistrictMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
public class TestController {

    @Autowired
    DicDistrictMapper dicDistrictMapper;


    @GetMapping("/test")
    public String test() {
        return "hello";
    }

    @GetMapping("/test-map")
    public String testMap() {
        Map<String, Object> map = Map.of("address_code", "110000");
        List<DicDistrict> dicDistricts = dicDistrictMapper.selectByMap(map);
        System.out.println(dicDistricts);
        return "test-map";
    }
}
