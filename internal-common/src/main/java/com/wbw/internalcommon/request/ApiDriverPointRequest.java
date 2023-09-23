package com.wbw.internalcommon.request;

import com.wbw.internalcommon.dto.PointDTO;
import lombok.Data;

import java.io.Serializable;

@Data
public class ApiDriverPointRequest implements Serializable {
    private Long carId;
    private PointDTO[] points;
}
