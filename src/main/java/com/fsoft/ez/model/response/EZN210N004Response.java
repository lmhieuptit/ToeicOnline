package com.fsoft.ez.model.response;

import lombok.Data;
@Data
public class EZN210N004Response {

    private String emplid;

    private String nameDisplay;

    private String jobIndicator;

    private String descr;

    private Boolean isGroupMember;
    
    private String account;
}
