package com.wbw.apipassenger.request;

import lombok.Data;

@Data
public class VerificationCodeDTD {

    private String passengerPhone;

    private String verificationCode;
}
