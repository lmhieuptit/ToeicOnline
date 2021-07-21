package com.fsoft.ez.common.model;

import java.time.LocalDate;

import javax.persistence.Column;

import lombok.Data;

@Data
public class UserInfoDTO {
     
    private String emplId;
    
    private String email;
    
    private String deptId;
    
    private String nameDisplay;
    
    private String deptName;
    
    private String account;
    
    private String descrshort;
    
    private String roleCode;
    
    private Long processId;
    
    private Boolean status;
    
    private String avatarUrl;
    
    private String coverImage;
    
    private String jobIndicator;
    
    private String phoneNumber;
    
    private String address;
    
    private LocalDate dateOfBirth;
    
    private String company;
}
