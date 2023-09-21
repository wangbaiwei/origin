package com.wbw.internalcommon.response;

import lombok.Data;

@Data
public class ForecastPriceResponse {
    private double price;
    private String cityCode;
    private String vehicleType;
    private Integer fareVersion;
    private String fareType;
}
