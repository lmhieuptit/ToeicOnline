package com.fsoft.ez.common.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserDTO {
    private String userId;
    private String account;
    private String mailAddress;
    private String sex;
    private String nameDisplay;
    private String jobIndicator;
    private String departmentName;
    private String roleName;
    private String avatarUrl;
    private LocalDate birthday;
}
