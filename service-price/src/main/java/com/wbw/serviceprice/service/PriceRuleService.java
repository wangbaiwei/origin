package com.wbw.serviceprice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wbw.internalcommon.constant.CommonStatusEnum;
import com.wbw.internalcommon.dto.PriceRule;
import com.wbw.internalcommon.dto.ResponseResult;
import com.wbw.serviceprice.mapper.PriceRuleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PriceRuleService {

    @Autowired
    private PriceRuleMapper priceRuleMapper;


    public ResponseResult add(PriceRule priceRule) {

        // 拼接fareType
        String cityCode = priceRule.getCityCode();
        String vehicleType = priceRule.getVehicleType();
        String fareType = cityCode + vehicleType;
        priceRule.setFareType(fareType);


        // 添加版本号
        Map<String, Object> map = new HashMap<>();
        map.put("city_ocde", cityCode);
        map.put("vehicle_type", vehicleType);


        QueryWrapper<PriceRule> priceRuleQueryWrapper = new QueryWrapper<>();
        priceRuleQueryWrapper.eq("city_code", cityCode);
        priceRuleQueryWrapper.eq("vehicle_type", vehicleType);
        priceRuleQueryWrapper.orderByDesc("fare_version");

        List<PriceRule> priceRules = priceRuleMapper.selectList(priceRuleQueryWrapper);
        int fareVersion = 0;
        if (priceRules.size() > 0) {
            return ResponseResult.fail(CommonStatusEnum.PRICE_RULE_EXISTS.getCode(), CommonStatusEnum.PRICE_RULE_EXISTS.getValue());
        }

        priceRule.setFareVersion(++fareVersion);
        priceRuleMapper.insert(priceRule);

        return ResponseResult.success();
    }


    public ResponseResult edit(PriceRule priceRule) {
        // 拼接fareType
        String cityCode = priceRule.getCityCode();
        String vehicleType = priceRule.getVehicleType();
        String fareType = cityCode + vehicleType;
        priceRule.setFareType(fareType);


        // 添加版本号
        Map<String, Object> map = new HashMap<>();
        map.put("city_ocde", cityCode);
        map.put("vehicle_type", vehicleType);


        QueryWrapper<PriceRule> priceRuleQueryWrapper = new QueryWrapper<>();
        priceRuleQueryWrapper.eq("city_code", cityCode);
        priceRuleQueryWrapper.eq("vehicle_type", vehicleType);
        priceRuleQueryWrapper.orderByDesc("fare_version");

        List<PriceRule> priceRules = priceRuleMapper.selectList(priceRuleQueryWrapper);
        int fareVersion = 0;
        if (priceRules.size() > 0) {
            PriceRule lasterPriceRule = priceRules.get(0);
            Double unitPricePerMile = lasterPriceRule.getUnitPricePerMile();
            Double unitPricePerMinute = lasterPriceRule.getUnitPricePerMinute();
            Double startFare = lasterPriceRule.getStartFare();
            Integer startMile = lasterPriceRule.getStartMile();
            if (unitPricePerMile.doubleValue() == priceRule.getUnitPricePerMile().doubleValue()
                    && unitPricePerMinute.doubleValue() == priceRule.getUnitPricePerMinute().doubleValue()
                    && startFare.doubleValue() == priceRule.getStartFare().doubleValue()
                    && startMile.intValue() == priceRule.getStartMile().intValue()) {
                return ResponseResult.fail(CommonStatusEnum.PRICE_RULE_NOT_CHANGE.getCode(), CommonStatusEnum.PRICE_RULE_NOT_CHANGE.getValue());

            }
            fareVersion = lasterPriceRule.getFareVersion();

        }

        priceRule.setFareVersion(++fareVersion);
        priceRuleMapper.insert(priceRule);

        return ResponseResult.success();
    }


}
