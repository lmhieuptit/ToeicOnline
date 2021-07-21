package com.fsoft.ez.model.response;

import lombok.Data;

@Data
public class EZN203N00Respose {
    private String userId;
    private Long newsId;
    /** true: like, false: unlike */
    private boolean likeFlg;
}
