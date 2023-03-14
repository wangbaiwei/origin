package com.wbw.servicemap.service;

import com.wbw.internalcommon.constant.AMapConfigConstants;
import com.wbw.internalcommon.dto.ResponseResult;
import com.wbw.servicemap.remote.MapDicDistrictClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DistrictService {

    @Autowired
    private MapDicDistrictClient mapDicDistrictClient;


    public ResponseResult initDicDistrict(String keywords) {


        // 解析结果
        String s = mapDicDistrictClient.dicDistrict(keywords);
        log.info("res: {}", s);


        // 插入数据库


        return ResponseResult.success();
    }
}
