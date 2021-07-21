package com.fsoft.ez.model.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UpdateContentCommentRequest {

	@ApiModelProperty(notes = "Id của comment muốn sửa nội dung")
	@NotNull(message = "{MSG_001}")
	private Long commentId;
	
	@ApiModelProperty(notes = "Nội dung comment chuẩn bị update")
	@NotEmpty(message = "{MSG_001}")
	private String newCommentContent;
}
