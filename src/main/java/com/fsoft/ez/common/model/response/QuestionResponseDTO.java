package com.fsoft.ez.common.model.response;

import java.util.List;

import com.fsoft.ez.model.response.EZN201N03Response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuestionResponseDTO {

    private Long questionId;

    private String title;

    private List<EZN201N03Response> voteOptionList;

    private String dueDate;

    private int numberOfChoice;

}
