package com.fsoft.ez.model.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * update news comment request
 *
 * @author DanNT3
 *
 */
@Data
public class News002Request {

    @NotNull(message = "{MSG_001}")
    private Long commentId;

    @NotNull(message = "{MSG_001}")
    private Long userId;

    @NotEmpty(message = "{MSG_001}")
    private String content;

}
