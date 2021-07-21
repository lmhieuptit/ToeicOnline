package com.fsoft.ez.model.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * add news comment request
 *
 * @author DanNT3
 *
 */
@Data
public class News001Request {

    @NotNull(message = "{MSG_001}")
    private Long userId;

    @NotNull(message = "{MSG_001}")
    private Long newsId;

    @NotEmpty(message = "{MSG_001}")
    private String content;

}
