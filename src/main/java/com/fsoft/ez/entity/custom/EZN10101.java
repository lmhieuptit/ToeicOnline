package com.fsoft.ez.entity.custom;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class EZN10101 {

	@Id
	@Column(name = "process_id")
	private Long processId;

	@Column(name = "process_name")
	private String processName;

	@Column(name = "approver_id")
	private Long approverId;

	@Column(name = "account")
	private String account;

	@Column(name = "job_indicator")
	private String jobIndicator;

	@Column(name = "create_date")
	private LocalDateTime createDate;

	@Column(name = "status")
	private boolean status;

	@Column(name = "deptid")
	private Long departmentId;

	@Column(name = "descr")
	private String departmentName;
	
	@Column(name="full_path")
	private String fullPath;
}
