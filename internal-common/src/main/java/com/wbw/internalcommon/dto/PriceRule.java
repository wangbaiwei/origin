package com.wbw.internalcommon.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PriceRule implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 城市编码
     */
    private String cityCode;

    /**
     * 车类型
     */
    private String vehicleType;

    /**
     * 起步计价
     */
    private Double startFare;

    /**
     * 起步里程
     */
    private Integer startMile;

    /**
     * 单程价格
     */
    private Double unitPricePerMile;

    /**
     * 每分钟计价
     */
    private Double unitPricePerMinute;

    /**
     * 运价版本
     */
    private Integer fareVersion;

    /**
     * 运价类型
     */
    private String fareType;
}
