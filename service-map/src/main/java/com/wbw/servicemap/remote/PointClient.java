package com.wbw.servicemap.remote;

import com.wbw.internalcommon.constant.AMapConfigConstants;
import com.wbw.internalcommon.dto.PointDTO;
import com.wbw.internalcommon.dto.ResponseResult;
import com.wbw.internalcommon.request.PointRequest;
import com.wbw.internalcommon.response.TerminalResponse;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.Charset;

@Service
@Slf4j
public class PointClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${amap.key}")
    private String amapKey;

    @Value("${amap.sid}")
    private String sid;

    public ResponseResult<TerminalResponse> upload(PointRequest pointRequest) {
        // 拼装请求的url
        StringBuilder url = new StringBuilder();
        url.append(AMapConfigConstants.POINTS_UPLOAD_URL);
        url.append("?");
        url.append("key=" + amapKey);
        url.append("&");
        url.append("sid=" + sid);
        url.append("&");
        url.append("tid=" + pointRequest.getTid());
        url.append("&");
        url.append("points=");

        StringBuilder pointsBuilder = new StringBuilder();
        for (PointDTO point : pointRequest.getPoints()) {

            pointsBuilder.append("[");
            pointsBuilder.append("{");
            String location = point.getLocation();
            String locatetime = point.getLocatetime();
            pointsBuilder.append("\"location\"");
            pointsBuilder.append(":");
            pointsBuilder.append("\"" + location + "\"");
            pointsBuilder.append(",");
            pointsBuilder.append("\"locatetime\"");
            pointsBuilder.append(":");
            pointsBuilder.append("\"" + locatetime + "\"");
            pointsBuilder.append("}");
            pointsBuilder.append("]");
        }

        String encode = URLEncoder.encode(pointsBuilder.toString(), Charset.defaultCharset());
        url.append(encode);
        log.info("轨迹点上传接口url:{}", url);
        ResponseEntity<String> forEntity = restTemplate.postForEntity(URI.create(url.toString()), null, String.class);
        String body = forEntity.getBody();

        JSONObject result = JSONObject.fromObject(body);
        log.info("轨迹点上传接口响应信息：{}", result);

        return ResponseResult.success();

    }
}
