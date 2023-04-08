package com.wbw.internalcommon.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderRequest {
    private String address;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime departTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orderTime;
    private String depature;
    private String deplongitude;
    private String depLatitude;
    private String destination;
    private String destinatitude;
    private String destLatitude;
    // 坐标加密标识 1：gcj-01,2:wgs84,3:bd-09,4:cgcs2000北斗,0:其它
    private String encrypt;
    // 运价类型编码
    private String fareType;


}
