package com.fsoft.ez.dto;

import lombok.Data;

@Data
public class NewsN10DTO {
    private Long voteOptionId;
    private String userId;
    /** vote: true, unvote: false */
    private boolean voteFlg;
}
