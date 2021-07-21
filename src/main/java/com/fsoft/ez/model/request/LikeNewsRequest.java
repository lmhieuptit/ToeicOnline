package com.fsoft.ez.model.request;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LikeNewsRequest {

	@ApiModelProperty(notes = "Id của bài viết mà người dùng bấm like hoặc unlike")
	@NotNull(message = "{MSG_001}")
	private Long newsId;
	
	@ApiModelProperty(notes = "Loại hành động, 1 là like, 0 là unlike")
	@NotNull(message = "{MSG_001}")
	private Integer likeType;
	
}
