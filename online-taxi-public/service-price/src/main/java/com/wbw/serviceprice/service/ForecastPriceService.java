package com.wbw.serviceprice.service;

import com.wbw.internalcommon.constant.CommonStatusEnum;
import com.wbw.internalcommon.dto.ResponseResult;
import com.wbw.internalcommon.request.ForecasePriceDTO;
import com.wbw.internalcommon.response.DirectionResponse;
import com.wbw.internalcommon.response.ForecastPriceResponse;
import com.wbw.serviceprice.dto.PriceRule;
import com.wbw.serviceprice.mapper.PriceRuleMapper;
import com.wbw.serviceprice.remote.ServiceMapClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ForecastPriceService {

    @Autowired
    private ServiceMapClient serviceMapClient;

    @Autowired
    private PriceRuleMapper priceRuleMapper;


    /**
     * 估算价格
     *
     * @param depLongitude
     * @param depLatitude
     * @param desLongitude
     * @param desLatitude
     * @return
     */
    public ResponseResult forecastPrice(String depLongitude, String depLatitude, String desLongitude, String desLatitude) {

        log.info("出发地的经度：{}, 出发地的维度：{}, 目的地的经度：{}, 目的地的维度：{}", depLongitude, depLatitude, desLongitude, desLatitude);
        ForecasePriceDTO forecasePriceDTO = ForecasePriceDTO.builder().build();
        forecasePriceDTO.setDepLongitude(depLongitude);
        forecasePriceDTO.setDepLatitude(depLatitude);
        forecasePriceDTO.setDesLongitude(desLongitude);
        forecasePriceDTO.setDesLatitude(desLatitude);

        log.info("调用地图服务，查询距离和时长");
        ResponseResult<DirectionResponse> direction = serviceMapClient.direction(forecasePriceDTO);
        Integer distance = direction.getData().getDistance();
        Integer duration = direction.getData().getDuration();
        log.info("距离：{}, 时长：{}", distance, duration);


        log.info("读取计价规则");

        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("city_code", "110000");
        queryMap.put("vehicle_type", "1");
        List<PriceRule> priceRules = priceRuleMapper.selectByMap(queryMap);

        if (priceRules.size() == 0) {
            return ResponseResult.fail(CommonStatusEnum.PRICE_RULE_EMPTY.getCode(), CommonStatusEnum.PRICE_RULE_EMPTY.getValue());
        }
        PriceRule priceRule = priceRules.get(0);


        log.info("根据距离，时长和计价规则，计算价格");
        double price = getPrice(distance, duration, priceRule);
        ForecastPriceResponse forecastPriceResponse = new ForecastPriceResponse();
        forecastPriceResponse.setPrice(price);
        return ResponseResult.success(forecastPriceResponse);

    }

    /**
     * 根据距离时长，计算最终价格
     * @param distance 距离
     * @param duration 时长
     * @param priceRule 计价规则
     * @return
     */
    private double getPrice(Integer distance, Integer duration, PriceRule priceRule) {
        BigDecimal price= new BigDecimal(0);
        // 起步价

        Double startFare = priceRule.getStartFare();
        BigDecimal startFareDecimal = new BigDecimal(startFare);
        price = price.add(startFareDecimal);

        // 里程碑
        // 总里程 m
        BigDecimal distanceBigDecimal = new BigDecimal(distance);
        // 总里程 km
        distanceBigDecimal = distanceBigDecimal.divide(new BigDecimal(1000), 2,  RoundingMode.HALF_UP );
        // 起步里程
        Integer startMile = priceRule.getStartMile();
        BigDecimal startMileBigDecimal = new BigDecimal(startMile);
        double distanceSubstrat = distanceBigDecimal.subtract(startMileBigDecimal).doubleValue();

        // 最终收费的里程数 km
        double mile = distanceSubstrat < 0 ? 0 : distanceSubstrat;
        BigDecimal mileDecimal = new BigDecimal(mile);

        // 计程单价 元/km
        Double unitPricePerMile = priceRule.getUnitPricePerMile();
        BigDecimal unitPricePerMileDecimal = new BigDecimal(unitPricePerMile);

        // 里程价格
        BigDecimal mileFare = mileDecimal.multiply(unitPricePerMileDecimal).setScale(2, RoundingMode.HALF_UP);
        price = price.add(mileFare);

        BigDecimal time = new BigDecimal(duration);
        // 时长的分钟数
        BigDecimal timeDecimal = time.divide(new BigDecimal(60), 2, RoundingMode.HALF_UP);
        // 计时单价
        Double unitPricePerMinute = priceRule.getUnitPricePerMinute();
        BigDecimal unitPricePerMinuteDecimal = new BigDecimal(unitPricePerMinute);
        // 时长费用
        BigDecimal timeFare = timeDecimal.multiply(unitPricePerMinuteDecimal);
        price = price.add(timeFare).setScale(2, RoundingMode.HALF_UP);

        return price.doubleValue();

    }

    public static void main(String[] args) {
        PriceRule priceRule = new PriceRule();
        priceRule.setUnitPricePerMile(1.8);
        priceRule.setUnitPricePerMinute(0.5);
        priceRule.setStartFare(10.0);
        priceRule.setStartMile(3);
//        System.out.println(getPrice(6500, 1800, priceRule));

    }
}
