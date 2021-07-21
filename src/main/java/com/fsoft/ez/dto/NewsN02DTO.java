package com.fsoft.ez.dto;

import java.util.List;

import lombok.Data;

/**
 * vote class
 */
@Data
public class NewsN02DTO {

    private String question;

    private List<NewsN03DTO> optionList;

    private String endDate;
}
