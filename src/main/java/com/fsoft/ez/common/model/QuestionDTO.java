package com.fsoft.ez.common.model;

import java.util.List;

import lombok.Data;

@Data
public class QuestionDTO {

    private String title;

    private List<String> answerList;

    private String dueDate;

    private int numberOfChoice;

}
