package com.wbw.internalcommon.constant;

public class OrderConstants {


    public static final int ORDER_STAT = 1;
    /**
     * 司机去接单
     */
    public static final int DRIVER_RECEIVE_ORDER = 2;
    /**
     * 司机去接乘客
     */
    public static final int DRIVER_TO_PICK_UP_PASSENGER = 3;
    /**
     * 司机到达乘客起点
     */
    public static final int DRIVER_ARRIVED_DEPATURE = 4;
    /**
     * 乘客上车，司机开始行程
     */
    public static final int PICK_UP_PASSENGER = 5;

    /**
     * 到达目的地，行程结束，未支付
     */
    public static final int PASSENGER_GETOFF = 6;
    /**
     * 发起收款
     */
    public static final int TO_START_PAY = 7;
    /**
     * 支付完成
     */
    public static final int SUCCESS_PAY = 8;
    /**
     * 订单取消
     */
    public static final int ORDER_CANCEL = 9;



}
