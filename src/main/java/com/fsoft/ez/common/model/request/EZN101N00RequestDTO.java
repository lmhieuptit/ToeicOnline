package com.fsoft.ez.common.model.request;

import lombok.Data;

@Data
public class EZN101N00RequestDTO {

	private Long processId;
	private String processName;
	private Long departmentId;
	private boolean status;
	private boolean option;
	private Long positionId;
	private Long userId;

}
