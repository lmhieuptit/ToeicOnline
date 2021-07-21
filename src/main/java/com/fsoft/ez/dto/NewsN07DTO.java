package com.fsoft.ez.dto;

import lombok.Data;

@Data
public class NewsN07DTO {
    private String userId;
    private Long newsId;
    /** true: like, false: unlike */
    private boolean likeFlg;
}
