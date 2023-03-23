package com.wbw.internalcommon.request;

import com.wbw.internalcommon.dto.PointDTO;
import lombok.Data;

@Data
public class PointRequest {

    private String tid;
    private String trid;
    private PointDTO[] points;
}
