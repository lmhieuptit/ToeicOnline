package com.fsoft.ez.model.request;

import lombok.Data;

@Data
public class EZN211N05Resquest {
    
    private Long newsId;

    /*
     * tu choi : 2
     * phe duyet : 1
     */
    private Integer status;
    
    private String reasonReject;
}
