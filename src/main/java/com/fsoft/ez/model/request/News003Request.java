package com.fsoft.ez.model.request;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * delete news comment request
 *
 * @author DanNT3
 *
 */
@Data
public class News003Request {

    @NotNull(message = "{MSG_001}")
    private Long commentId;

    @NotNull(message = "{MSG_001}")
    private Long userId;

}
