package com.fsoft.ez.model.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class EZN102001Resquest {

	@NotEmpty(message = "{MSG_0001}")
	@Size(max = 75)
	private String processName;

	private String departmentId;
	private boolean status;
	private boolean option;
	private Long approverId;
	private String jobIndicator;
}
