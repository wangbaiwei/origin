package com.wbw.serviceorder.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wbw.internalcommon.constant.CommonStatusEnum;
import com.wbw.internalcommon.constant.OrderConstants;
import com.wbw.internalcommon.dto.OrderInfo;
import com.wbw.internalcommon.dto.PriceRule;
import com.wbw.internalcommon.dto.ResponseResult;
import com.wbw.internalcommon.request.OrderRequest;
import com.wbw.internalcommon.response.TerminalResponse;
import com.wbw.internalcommon.utils.RedisPrefixUtils;
import com.wbw.serviceorder.mapper.OrderInfoMapper;
import com.wbw.serviceorder.remote.ServiceMapClient;
import com.wbw.serviceorder.remote.ServicePriceClient;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 王佰伟
 * @since 2023-04-08
 */

@Service
@Slf4j
public class OrderInfoService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ServicePriceClient servicePriceClient;

    @Autowired
    private ServiceMapClient serviceMapClient;

    @Autowired
    ServerDriverUserClient serverDriverUserClient;

    public ResponseResult add(OrderRequest orderRequest) {
        ResponseResult<Boolean> avaliableDriver = serverDriverUserClient.isAvaliableDriver(orderRequest.getAddress());
        log.info("测试城市是否有司机：{}", avaliableDriver.getData());
        if (!avaliableDriver.getData()) {
            ResponseResult.fail(CommonStatusEnum.CITY_DRIVER_EMPTY);
        }

        ResponseResult<Boolean> aNew = servicePriceClient.isNew(orderRequest.getFareType(), orderRequest.getFareVersion());
        if (!aNew.getData()) {
            ResponseResult.fail(CommonStatusEnum.PRICE_RULE_CHANGED.getCode(), CommonStatusEnum.PRICE_RULE_CHANGED.getValue());
        }


        // 判断有正在进行的订单不允许下单
//        if (isOrderGoingOn(orderRequest.getPassengerId()) > 0) {
//            return ResponseResult.fail(CommonStatusEnum.ORDER_GING_ON);
//        }
        // 校验当前下单设备是否是黑名单设备
//        if (isBlaceDevice(orderRequest)) {
//            return ResponseResult.fail(CommonStatusEnum.DEVICE_IS_BLACK);
//        }

        // 判断：下单的城市计价规则是否存在
        if (!isPriceRuleExists(orderRequest)) {
            return ResponseResult.fail(CommonStatusEnum.CITY_SERVICE_NOT_SERVICE);
        }

        OrderInfo orderInfo = new OrderInfo();
        BeanUtils.copyProperties(orderRequest, orderInfo);
        orderInfo.setOrderStatus(OrderConstants.ORDER_START);
        LocalDateTime now = LocalDateTime.now();
        orderInfo.setGmtCreate(now);
        orderInfo.setGmtModified(now);

        orderInfoMapper.insert(orderInfo);

        // 派单
        dispatchRealTimeOrder(orderInfo);
        return ResponseResult.success();

    }

    /**
     * 实时订单派单逻辑
     *
     * @param orderInfo
     * @return
     */
    public void dispatchRealTimeOrder(OrderInfo orderInfo) {
        String depLongitude = orderInfo.getDepLongitude();
        String depLatitude = orderInfo.getDepLatitude();
        String center = StringUtils.join(depLatitude, ",", depLongitude);

        List<Integer> radiusList = new ArrayList<>();
        radiusList.add(2000);
        radiusList.add(4000);
        radiusList.add(5000);

        ResponseResult<List<TerminalResponse>> listResponseResult = null;
        for (Integer radius : radiusList) {
            listResponseResult = serviceMapClient.terminalAroundSearch(center, radius);
            log.info("在半径为：{}出寻找车辆", radius);
            log.info(listResponseResult.getData().toString());
            // 获得终端

            // 解析终端
            JSONArray result = JSONArray.fromObject(listResponseResult.getData());
            for (int i = 0; i < result.size(); i++) {
                JSONObject jsonObject = result.getJSONObject(i);
                String carIdStr = jsonObject.getString("carId");
                long carId = Long.parseLong(carIdStr);
            }


            // 根据解析出来的终端，查询车辆

            // 找到符合的车辆，进行派单
        }

    }

    public boolean isPriceRuleExists(OrderRequest orderRequest) {
        String fareType = orderRequest.getFareType();
        int index = fareType.indexOf("$");
        String cityCode = fareType.substring(0, index);
        String vehicleType = fareType.substring(index + 1);

        PriceRule priceRule = new PriceRule();
        priceRule.setCityCode(cityCode);
        priceRule.setVehicleType(vehicleType);
        ResponseResult<Boolean> booleanResponseResult = servicePriceClient.ifPriceExists(priceRule);
        return booleanResponseResult.getData();

    }

    /**
     * 黑白名单校验
     *
     * @param orderRequest
     * @return
     */
    private boolean isBlaceDevice(OrderRequest orderRequest) {
        // 生成key
        String deviceCodeKey = RedisPrefixUtils.blackDeviceCodePrefix + orderRequest.getDeviceCode();
        Boolean aBoolean = stringRedisTemplate.hasKey(deviceCodeKey);
        if (aBoolean) {
            String s = stringRedisTemplate.opsForValue().get(deviceCodeKey);
            int i = Integer.parseInt(s);
            if (i >= 2) {
                // 当前设备超过下单次数
                return true;
            } else {
                stringRedisTemplate.opsForValue().increment(deviceCodeKey);
            }
        } else {
            stringRedisTemplate.opsForValue().setIfAbsent(deviceCodeKey, "1", 1L, TimeUnit.HOURS);
        }
        return false;
    }

    /**
     * 是否有 业务正在进行中
     *
     * @param passengerId
     * @return
     */
    private Integer isOrderGoingOn(Long passengerId) {
        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("passenger_id", passengerId);
        queryWrapper.and(wrapper -> wrapper.eq("order_status", OrderConstants.ORDER_START))
                .or().eq("order_status", OrderConstants.DRIVER_RECEIVE_ORDER)
                .or().eq("order_status", OrderConstants.DRIVER_TO_PICK_UP_PASSENGER)
                .or().eq("order_status", OrderConstants.DRIVER_ARRIVED_DEPATURE)
                .or().eq("order_status", OrderConstants.PICK_UP_PASSENGER)
                .or().eq("order_status", OrderConstants.PASSENGER_GETOFF)
                .or().eq("order_status", OrderConstants.TO_START_PAY);

        Integer validOrderNumber = orderInfoMapper.selectCount(queryWrapper);
        return validOrderNumber;
    }

}
