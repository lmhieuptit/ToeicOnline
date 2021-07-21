package com.fsoft.ez.model.request;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class EZN203N00Request {
    @NotNull(message = "{MSG_0001}")
    private String userId;
//	@NotNull(message = "{MSG_0001}")
    private Long newsId;
//	/** true: like, false: unlike */
//	private boolean likeFlg;
}
