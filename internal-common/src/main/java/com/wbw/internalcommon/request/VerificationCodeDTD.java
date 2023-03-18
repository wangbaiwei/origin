package com.wbw.internalcommon.request;

import lombok.Data;

@Data
public class VerificationCodeDTD {

    private String passengerPhone;

    private String verificationCode;

    private String driverPhone;
}
