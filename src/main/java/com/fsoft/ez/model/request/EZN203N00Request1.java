package com.fsoft.ez.model.request;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class EZN203N00Request1 {

	@NotNull
	private Long limit;
	
	@NotNull
	private Long offset;
	
}
