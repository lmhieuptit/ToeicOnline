package com.fsoft.ez.common.model.response;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EZN107N00ResponseDTO {

    private String title;
    private String content;
    private String createUser;
    private LocalDateTime createDate;
    private Integer status;
    private String approverUser;
    private LocalDateTime approverDate;
    private String reasonReject;
}
