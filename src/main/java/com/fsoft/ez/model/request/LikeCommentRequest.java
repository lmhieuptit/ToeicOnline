package com.fsoft.ez.model.request;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LikeCommentRequest {

	@ApiModelProperty(notes = "Id của bài viết mà chứa comment muốn like hoặc unlike")
	private Long newsId;
	
	@ApiModelProperty(notes = "Id của comment muốn like hoặc unlike")
	@NotNull(message = "{MSG_001}")
	private Long commentId;
	
	@ApiModelProperty(notes = "Loại hành động, 1 là like, 0 là unlike")
	private Integer likeType;
}
