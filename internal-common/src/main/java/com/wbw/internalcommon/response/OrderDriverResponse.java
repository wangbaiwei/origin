package com.wbw.internalcommon.response;

import lombok.Data;

@Data
public class OrderDriverResponse {

    private Long driverId;
    private String driverPhone;
    private Long carId;

}