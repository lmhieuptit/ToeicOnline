package com.fsoft.ez.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class EZN302N01DTO {
    private Long userIdFrom;
    private Long userIdTo;
    private String happyBirthdayText;
    private LocalDateTime date;
    private String createUser;
    private LocalDateTime createDate;
}
