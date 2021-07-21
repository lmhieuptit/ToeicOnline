package com.fsoft.ez.model.request;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CreateUserRequest {
    private String emplid;
    private String deptid;
    private String company;
    private String account;
    private String emailAddr;
    private String address;
    private String phoneNumber;
    private String lowerEmailAddr;
    private String nameDisplay;
    private String hrStatus;
    private String location;
    private String sex;
    private String jobcode;
    private String setidJobcode;
    private String setidLocation;
    private String contractType;
    private String emplRcd;
    private String jobIndicator;
    private String type;
    private String tenantId;
    private LocalDate dateOfBirth;
    private String avatarUrl;
    private String coverImage;
    List<UserRoleRequest> roles;
}
