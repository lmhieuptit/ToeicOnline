package com.fsoft.ez.common.model.response;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Data;

@Data
public class UserInfoResponseDTO implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 868961565477993495L;

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
