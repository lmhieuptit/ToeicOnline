package com.fsoft.ez.common.model.response;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EZN106N00ResponseDTO {

    private String title;
    private String content;
    private String createUser;
    private LocalDateTime createDate;
    private Integer status;
    private String approverUser;
    private LocalDateTime approverDate;
    private String reasonReject;
}
