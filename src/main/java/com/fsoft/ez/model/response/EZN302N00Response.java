package com.fsoft.ez.model.response;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class EZN302N00Response {

    private String userId;

    private String userName;

    private String dayOfBirth;

    private Integer age;

    private String avatarUrl;

    private String department;

    private String departmentCode;

}
