package com.fsoft.ez.common.model.response;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class EZN101ResponseDTO {

	private Long processId;
	private String processName;
	private Long approverId;
	private Long UserId;
	private String account;
	private Long positionId;
	private String positionName;
	private LocalDateTime createDate;
	private boolean status;
	private Long departmentId;
	private String departmentName;
}
