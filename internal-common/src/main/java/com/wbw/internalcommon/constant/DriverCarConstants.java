package com.wbw.internalcommon.constant;

public class DriverCarConstants {
    /**
     * 司机车辆关系状态：绑定
     */
    public static int DRIVER_CAR_BIND = 1;
    /**
     * 司机车辆关系状态：解绑
     */
    public static int DRIVER_CAR_UNBIND = 2;
    /**
     * 司机状态有效
     */
    public static int DRIVER_STATE_VALID = 1;
    /**
     * 司机状态无效
     */
    public static int DRIVER_STATE_INVALID = 0;
    /**
     * 司机存在
     */
    public static int DRIVER_EXISTS = 1;
    /**
     * 司机不存在
     */
    public static int DRIVER_NOT_EXISTS = 0;


    /**
     * 司机工作状态：0：收车
     */
    public static long DRIVER_WORK_STATUS_STOP = 0;

    /**
     * 司机工作状态：1：出车
     */
    public static long DRIVER_WORK_STATUS_START = 1;

    /**
     * 司机工作状态：2：暂停
     */
    public static long DRIVER_WORK_STATUS_SUSPEND = 2;


}
