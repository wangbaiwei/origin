package com.wbw.internalcommon.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ForecasePriceDTO {

    private String depLatitude;
    private String depLongitude;
    private String desLatitude;
    private String desLongitude;
    private String cityCode;
    private String vehicleType;
}
