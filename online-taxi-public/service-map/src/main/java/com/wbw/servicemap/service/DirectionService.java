package com.wbw.servicemap.service;

import com.wbw.internalcommon.dto.ResponseResult;
import com.wbw.internalcommon.response.DirectionResponse;
import com.wbw.servicemap.remote.MapDirectionClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DirectionService {

    @Autowired
    private MapDirectionClient mapDirectionClient;


    /**
     * 根据起点经纬度和终点经纬度获取距离和时长
     * @param depLongitude
     * @param depLatitude
     * @param desLongitude
     * @param desLatitude
     * @return
     */
    public ResponseResult driving(String depLongitude, String depLatitude, String desLongitude, String desLatitude) {

        log.info("出发地的经度：{}", depLongitude);
        log.info("出发地的维度：{}", depLatitude);
        log.info("目的地的经度：{}", desLongitude);
        log.info("目的地的维度：{}", desLatitude);

        log.info("调用地图服务，查询距离和时长");
        DirectionResponse direction = mapDirectionClient.direction(depLongitude, depLatitude, desLongitude, desLatitude);

        log.info("读取计价规则");
        log.info("根据距离，时长和计价规则，计算价格");

        return ResponseResult.success(direction);

    }
}
