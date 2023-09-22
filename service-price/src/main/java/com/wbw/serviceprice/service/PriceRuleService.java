package com.wbw.serviceprice.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wbw.internalcommon.constant.CommonStatusEnum;
import com.wbw.internalcommon.dto.PriceRule;
import com.wbw.internalcommon.dto.ResponseResult;
import com.wbw.serviceprice.mapper.PriceRuleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class PriceRuleService {

    @Autowired
    private PriceRuleMapper priceRuleMapper;


    public ResponseResult add(PriceRule priceRule) {

        // 拼接fareType
        String cityCode = priceRule.getCityCode();
        String vehicleType = priceRule.getVehicleType();
        String fareType = cityCode + "$" + vehicleType;
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
            fareVersion = priceRules.get(0).getFareVersion();
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


    /**
     * 判断当前城市和车型的计价规则是否存在
     *
     * @param priceRule
     * @return
     */
    public ResponseResult<Boolean> ifExists(PriceRule priceRule) {
        log.info("请求信息：【{}】", priceRule);
        String cityCode = priceRule.getCityCode();
        String vehicleType = priceRule.getVehicleType();
        QueryWrapper<PriceRule> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("city_code", cityCode);
        queryWrapper.eq("vehicle_type", vehicleType);
        queryWrapper.orderByDesc("fare_version");
        List<PriceRule> priceRules = priceRuleMapper.selectList(queryWrapper);

        if (priceRules.size() > 0) {
            return ResponseResult.success(true);
        } else {
            return ResponseResult.fail(false);
        }
    }


    public ResponseResult<PriceRule> getNewestVersion(@RequestParam String fareType) {

        LambdaQueryWrapper<PriceRule> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PriceRule::getFareType, fareType).orderByDesc(PriceRule::getFareVersion);

        List<PriceRule> priceRules = priceRuleMapper.selectList(wrapper);
        if (priceRules.size() > 0) {
            return ResponseResult.success(priceRules.get(0));
        } else {
            return ResponseResult.fail(CommonStatusEnum.PRICE_RULE_EMPTY);
        }
    }

    public ResponseResult<Boolean> isNew(@RequestParam String fareType, @RequestParam int fareVersion) {
        ResponseResult<PriceRule> newestVersion = getNewestVersion(fareType);
        if (newestVersion.getCode() == CommonStatusEnum.PRICE_RULE_EMPTY.getCode()) {
            return ResponseResult.success(false);
        }

        PriceRule priceRule = newestVersion.getData();
        Integer versionDB = priceRule.getFareVersion();
        if (versionDB > fareVersion) {
            return ResponseResult.success(false);
        } else {
            return ResponseResult.success(true);
        }


    }


}
