package com.fsoft.ez.model.request;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class EZN203N02Request {

    private Long commentId;

    private Long parentCommentId;

    private String userId;

    @NotNull(message = "{MSG_0001}")
    private Long newsId;

    @NotNull(message = "{MSG_0001}")
    private String comment;
}
