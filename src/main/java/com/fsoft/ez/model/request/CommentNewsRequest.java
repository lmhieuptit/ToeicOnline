package com.fsoft.ez.model.request;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CommentNewsRequest {

	@ApiModelProperty(notes = "Id của bài viết muốn comment")
	@NotNull(message = "{MSG_001}")
	private Long newsId;
	
	@ApiModelProperty(notes = "Nội dung của comment")
	private String commentContent;
	
	@ApiModelProperty(notes = "Id của comment muốn reply. Để null hoặc rỗng nếu đây là comment trực tiếp vào bài viết, ko phải reply cho một comment khác!")
	private Long parentCommentId;
}
