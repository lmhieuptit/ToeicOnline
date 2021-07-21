package com.fsoft.ez.model.response;

import java.util.List;

import lombok.Data;

@Data
public class EZN201N02Response {

    private Long voteId;

    private String title;

    private List<EZN201N03Response> voteOptionList;

    private String endDate;

    private Long numberOfChoice;

    private Long limitFlag;

}
