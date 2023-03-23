package com.wbw.servicemap.remote;

import com.wbw.internalcommon.constant.AMapConfigConstants;
import com.wbw.internalcommon.dto.ResponseResult;
import com.wbw.internalcommon.response.TerminalResponse;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class TerminalClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${amap.key}")
    private String amapKey;

    @Value("${amap.sid}")
    private String sid;

    public ResponseResult<TerminalResponse> add(String name, String desc) {
        // 拼装请求的url
        StringBuilder url = new StringBuilder();
        url.append(AMapConfigConstants.TERMINAL_ADD_URL);
        url.append("?");
        url.append("key=" + amapKey);
        url.append("&");
        url.append("sid=" + sid);
        url.append("&");
        url.append("name=" + name);
        url.append("&");
        url.append("desc=" + desc);

        log.info("创建终端接口url:{}", url);

        ResponseEntity<String> forEntity = restTemplate.postForEntity(url.toString(), null, String.class);
        String body = forEntity.getBody();
        JSONObject result = JSONObject.fromObject(body);
        log.info("创建终端接口响应信息：{}", result);
        JSONObject data = result.getJSONObject("data");
        String tid = data.getString("tid");

        TerminalResponse terminalResponse = new TerminalResponse();
        terminalResponse.setTid(tid);
        return ResponseResult.success(terminalResponse);

    }
}

