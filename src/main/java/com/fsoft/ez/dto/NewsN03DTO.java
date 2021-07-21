package com.fsoft.ez.dto;

import java.util.List;

import lombok.Data;

/**
 * option class
 */
@Data
public class NewsN03DTO {
    private Long optionId;
    private String option;
    private List<NewsN04DTO> userVotedList;
}
