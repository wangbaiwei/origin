package com.wbw.servicemap.remote;

import com.wbw.internalcommon.constant.AMapConfigConstants;
import com.wbw.internalcommon.dto.ResponseResult;
import com.wbw.internalcommon.response.ServiceResponse;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ServiceClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${amap.key}")
    private String amapKey;

    public ResponseResult add(String name) {
        // 拼装请求的url
        StringBuilder url = new StringBuilder();
        url.append(AMapConfigConstants.SERVICE_ADD_URL);
        url.append("?");
        url.append("key=" + amapKey);
        url.append("&");
        url.append("name=" + name);

        ResponseEntity<String> forEntity = restTemplate.postForEntity(url.toString(), null, String.class);
        String body = forEntity.getBody();
        JSONObject result = JSONObject.fromObject(body);
        JSONObject data = result.getJSONObject("data");
        String sid = data.getString("sid");

        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setSid(sid);
        return ResponseResult.success(serviceResponse);

    }

}
