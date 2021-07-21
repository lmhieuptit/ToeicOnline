package com.fsoft.ez.dto;

import lombok.Data;

@Data
public class NewsN08DTO {
    private String userId;
    private Long newsId;
    private Long commentId;
    /** true: like, false: unlike */
    private boolean likeFlg;
}
