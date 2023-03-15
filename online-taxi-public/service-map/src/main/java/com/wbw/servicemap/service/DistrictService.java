package com.wbw.servicemap.service;

import com.wbw.internalcommon.constant.AMapConfigConstants;
import com.wbw.internalcommon.constant.CommonStatusEnum;
import com.wbw.internalcommon.dto.DicDistrict;
import com.wbw.internalcommon.dto.ResponseResult;
import com.wbw.servicemap.mapper.DicDistrictMapper;
import com.wbw.servicemap.remote.MapDicDistrictClient;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DistrictService {

    @Autowired
    private MapDicDistrictClient mapDicDistrictClient;

    @Autowired
    private DicDistrictMapper dicDistrictMapper;


    public ResponseResult initDicDistrict(String keywords) {


        // 解析结果
        String dicDistrictResult = mapDicDistrictClient.dicDistrict(keywords);
        log.info("res: {}", dicDistrictResult);


        JSONObject dicDistrictJsonObject = JSONObject.fromObject(dicDistrictResult);
        int status = dicDistrictJsonObject.getInt(AMapConfigConstants.STATUS);

        if (status != 1) {
            return ResponseResult.fail(CommonStatusEnum.MAP_DISTRICT_ERROR.getCode(), CommonStatusEnum.MAP_DISTRICT_ERROR.getValue());
        }
        // 插入数据库

        JSONArray countryJsonArray = dicDistrictJsonObject.getJSONArray(AMapConfigConstants.DISTRICTS);
        for (int country = 0; country < countryJsonArray.size(); country++) {
            JSONObject countryJsonObject = countryJsonArray.getJSONObject(country);
            String countryCode = countryJsonObject.getString(AMapConfigConstants.ADCODE);
            String countryName = countryJsonObject.getString(AMapConfigConstants.NAME);
            String countryAddressCode = "0";
            String countryLevel = countryJsonObject.getString(AMapConfigConstants.LEVEL);

            // 数据库对象
            insertDicDistrict(countryCode, countryName, countryLevel, countryAddressCode);
            JSONArray proviceJsonArray = countryJsonObject.getJSONArray(AMapConfigConstants.DISTRICTS);

            for (int p = 0; p < proviceJsonArray.size(); p++) {
                JSONObject proviceJsonObject = proviceJsonArray.getJSONObject(p);
                String proviceCode = proviceJsonObject.getString(AMapConfigConstants.ADCODE);
                String proviceName = proviceJsonObject.getString(AMapConfigConstants.NAME);
                String proviceAddressCode = countryCode;
                String proviceLevel = proviceJsonObject.getString(AMapConfigConstants.LEVEL);
                insertDicDistrict(proviceCode, proviceName, proviceLevel, proviceAddressCode);
                JSONArray cityJsonArray = proviceJsonObject.getJSONArray(AMapConfigConstants.DISTRICTS);

                for (int city = 0; city < cityJsonArray.size(); city++) {
                    JSONObject cityJsonObject = cityJsonArray.getJSONObject(city);
                    String cityCode = cityJsonObject.getString(AMapConfigConstants.ADCODE);
                    String cityName = cityJsonObject.getString(AMapConfigConstants.NAME);
                    String cityAddressCode = proviceCode;
                    String cityLevel = cityJsonObject.getString(AMapConfigConstants.LEVEL);

                    if (AMapConfigConstants.STREET.equals(cityLevel)) {
                        continue;
                    }
                    insertDicDistrict(cityCode, cityName, cityLevel, cityAddressCode);
                    JSONArray districtJsonArray = cityJsonObject.getJSONArray(AMapConfigConstants.DISTRICTS);

                    for (int d = 0; d < districtJsonArray.size(); d++) {
                        JSONObject districtJsonObject = districtJsonArray.getJSONObject(d);
                        String districtCode = districtJsonObject.getString(AMapConfigConstants.ADCODE);
                        String districtName = districtJsonObject.getString(AMapConfigConstants.NAME);
                        String districtAddressCode = cityCode;
                        String districtLevel = districtJsonObject.getString(AMapConfigConstants.LEVEL);
                        if (AMapConfigConstants.STREET.equals(cityLevel)) {
                            continue;
                        }
                        insertDicDistrict(districtCode, districtName, districtLevel, districtAddressCode);
                    }
                }
            }
        }
        return ResponseResult.success();
    }

    public void insertDicDistrict(String addressCode, String addressName, String level, String parentAddressCode) {
        DicDistrict district = new DicDistrict();
        district.setAddressCode(addressCode);
        district.setAddressName(addressName);
        district.setLevel(generateLevel(level));
        district.setParentAddressCode(parentAddressCode);
        // 插入数据库
        dicDistrictMapper.insert(district);
    }


    public int generateLevel(String level) {
        int levelInt = 0;
        if (level.trim().equals("county")) {
            levelInt = 0;
        } else if (level.trim().equals("province")) {
            levelInt = 1;
        } else if (level.trim().equals("city")) {
            levelInt = 2;
        } else if (level.trim().equals("district")) {
            levelInt = 3;
        } else if (level.trim().equals("street")) {
            levelInt = 4;
        }
        return levelInt;
    }
}
