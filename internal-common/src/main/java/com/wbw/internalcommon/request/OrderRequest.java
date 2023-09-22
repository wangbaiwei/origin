package com.wbw.internalcommon.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class OrderRequest {
    /**
     * 乘客ID
     */
    private Long passengerId;
    /**
     * 乘客手机号
     */
    private String passengerPhone;
    private String address;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime departTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orderTime;
    private String departure;
    private String depLongitude;
    private String depLatitude;
    private String destination;
    private String destLongitude;
    private String destLatitude;
    /**
     * 坐标加密标识 1：gcj-01,2:wgs84,3:bd-09,4:cgcs2000北斗,0:其它
     */
    private Integer encrypt;
    /**
     * 运价类型编码
     */
    private String fareType;
    /**
     * 运价版本
     */
    private Integer fareVersion;
    /**
     * 设备编号
     */
    private String deviceCode;



}
