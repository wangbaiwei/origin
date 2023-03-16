package com.wbw.servicemap.remote;

import com.wbw.internalcommon.constant.AMapConfigConstants;
import com.wbw.internalcommon.response.DirectionResponse;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class MapDirectionClient {

    @Value("${amap.key}")
    private String amapKey;

    @Autowired
    private RestTemplate restTemplate;

    public DirectionResponse direction(String depLongitude, String depLatitude, String desLongitude, String desLatitude) {

        // 组装请求参数url
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(AMapConfigConstants.DIRECTION_URL);
        urlBuilder.append("?");
        urlBuilder.append("origin=" + depLongitude + "," + depLatitude);
        urlBuilder.append("&");
        urlBuilder.append("destination=" + desLongitude + "," + desLatitude);
        urlBuilder.append("&");
        urlBuilder.append("extensions=base");
        urlBuilder.append("&");
        urlBuilder.append("output=json");
        urlBuilder.append("&");
        urlBuilder.append("key=" + amapKey);
        log.info("url: {}", urlBuilder);

        // 调用高德接口
        ResponseEntity<String> directionEntity = restTemplate.getForEntity(urlBuilder.toString(), String.class);
        String directionString = directionEntity.getBody();
        log.info("高德地图：路径规划，返回信息：{}", directionString);

        // 解析接口
        return parseDirectionEntity(directionString);
    }

    private DirectionResponse parseDirectionEntity(String directionString) {
        DirectionResponse directionResponse = null;
        try {
            JSONObject result = JSONObject.fromObject(directionString);
            if (result.has(AMapConfigConstants.STATUS)) {
                if (result.has(AMapConfigConstants.STATUS)) {
                    int status = result.getInt(AMapConfigConstants.STATUS);
                    if (status == 1) {
                        directionResponse = new DirectionResponse();
                        if (result.has(AMapConfigConstants.ROUTE)) {
                            JSONObject routeObject = result.getJSONObject(AMapConfigConstants.ROUTE);
                            JSONArray pathsArray = routeObject.getJSONArray(AMapConfigConstants.PATHS);
                            JSONObject pathObject = pathsArray.getJSONObject(0);
                            if (pathObject.has(AMapConfigConstants.DISTANCE)) {
                                int distance = pathObject.getInt(AMapConfigConstants.DISTANCE);
                                directionResponse.setDistance(distance);
                            }
                            if (pathObject.has(AMapConfigConstants.DURATION)) {
                                int duration = pathObject.getInt(AMapConfigConstants.DURATION);
                                directionResponse.setDuration(duration);
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {

        }
        return directionResponse;

    }
}
