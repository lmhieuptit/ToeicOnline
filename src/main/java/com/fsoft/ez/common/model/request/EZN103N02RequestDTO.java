package com.fsoft.ez.common.model.request;

import lombok.Data;

@Data
public class EZN103N02RequestDTO {

    private Long newsId;
    private Integer confirmStatus;
    private String reasonReject;

}
