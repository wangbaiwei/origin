package com.wbw.servicemap.remote;

import com.wbw.internalcommon.constant.AMapConfigConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class MapDicDistrictClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${amap.key}")
    private String amapKey;


    public String dicDistrict(String keywords) {
        // 拼装请求的url
        StringBuilder url = new StringBuilder();
        url.append(AMapConfigConstants.DISTRICT_URL);
        url.append("?");
        url.append("keywords="+keywords);
        url.append("&");
        url.append("subdistrict=3");
        url.append("&");
        url.append("key="+amapKey);
        log.info("url: {}",url);


        ResponseEntity<String> forEntity = restTemplate.getForEntity(url.toString(), String.class);
        return forEntity.getBody();

    }

}
