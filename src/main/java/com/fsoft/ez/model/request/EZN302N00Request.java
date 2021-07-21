package com.fsoft.ez.model.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class EZN302N00Request {

	@NotNull(message = "{MSG_001}")
	private Long userIdFrom;
	
	@NotNull(message = "{MSG_001}")
	private Long userIdTo;
	
	@NotEmpty(message = "{MSG_001}")
	private String birthdayGreetings;
	
}
