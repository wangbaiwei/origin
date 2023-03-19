package com.wbw.internalcommon.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class PassengerUser {

    private long id;
    private LocalDate gmtCreate;
    private LocalDate gmtUpdate;
    private String passengerPhone;
    private String passengerName;
    private byte passengerGender;
    private byte state;
    private String profilePhoto;
}
