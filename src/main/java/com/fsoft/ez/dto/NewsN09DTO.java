package com.fsoft.ez.dto;

import lombok.Data;

@Data
public class NewsN09DTO {
    private Long commentId;
    private Long parentCommentId;
    private String userId;
    private Long newsId;
    private String comment;
}
