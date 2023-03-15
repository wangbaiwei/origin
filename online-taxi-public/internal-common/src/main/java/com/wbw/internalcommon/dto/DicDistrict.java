package com.wbw.internalcommon.dto;

import lombok.Data;

@Data
public class DicDistrict {

    private String addressCode;
    private String addressName;
    private String parentAddressCode;
    private int level;
}
