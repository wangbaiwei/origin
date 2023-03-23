package com.wbw.internalcommon.request;

import com.wbw.internalcommon.dto.PointDTO;
import lombok.Data;

@Data
public class ApiDriverPointRequest {
    private Long carId;
    private PointDTO[] points;
}
