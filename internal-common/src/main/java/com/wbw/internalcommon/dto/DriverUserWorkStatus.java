package com.wbw.internalcommon.dto;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author 王佰伟
 * @since 2023-03-19
 */
@Data
public class DriverUserWorkStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long driverId;

    private Integer workStatus;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

}
