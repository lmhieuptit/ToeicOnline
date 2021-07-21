package com.fsoft.ez.model.response;

import lombok.Data;

@Data
public class EZN203N01Respose {
    private String userId;
    private Long commentId;
    /** true: like, false: unlike */
    private boolean likeFlg;
}
