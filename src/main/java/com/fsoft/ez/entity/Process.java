/**
 * Copyright © 2020 Suntory Corporation. All Rights Reserved.
 **/
package com.fsoft.ez.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.springframework.data.annotation.LastModifiedBy;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
@Table(name = "process")
public class Process implements Serializable {
	/**
	 * シリアルバージョンUID
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "process_id", nullable = false)
	private Long processId;

	@Column(name = "department_id", nullable = true)
	private String departmentId;

	@Column(name = "approver_id", nullable = true)
	private Long approverId;

	@Column(name = "process_name", nullable = false)
	private String processName;

	@Column(name = "job_indicator", nullable = false)
	private String jobIndicator;

	/**
	 * 0: active, 1: inactive
	 */
	@Column(name = "status", nullable = true, columnDefinition = "TINYINT(1)")
	private Boolean status;

	@Column(name = "create_user", nullable = false)
	@LastModifiedBy
	private String createUser;
	
	@Column(name = "create_date", nullable = true)
	private LocalDateTime createDate;

	@Column(name = "update_date", nullable = true)
	private LocalDateTime updateDate;

	/**
	 * 0: not delete, 1: delete
	 */
	@Column(name = "delete_flag", nullable = false, columnDefinition = "TINYINT(1)")
	private Boolean deleteFlag;

	@PrePersist
	protected void onCreate() {
		this.createDate = this.updateDate = LocalDateTime.now();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updateDate = LocalDateTime.now();
	}
}