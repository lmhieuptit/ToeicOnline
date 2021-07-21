package com.fsoft.ez.model.request;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class NewsFeedUpViewN00Request {

	@ApiModelProperty("id của bài viết muốn tăng lượt xem")
	@NotNull(message = "{MSG_001}")
	private Long newsId;
	
}
