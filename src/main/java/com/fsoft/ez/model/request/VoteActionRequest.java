package com.fsoft.ez.model.request;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class VoteActionRequest {

	@NotNull(message = "{MSG_001}")
	@ApiModelProperty(notes = "vote id")
	private Long voteId;
	
	@ApiModelProperty(notes = "Gửi 1 nếu vote, gửi 0 nếu unvote")
	private Integer voteType;
	
}
