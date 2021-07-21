package com.fsoft.ez.entity.custom;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class EZN10102 {

	@Id
	@Column(name = "job_indicator")
	private String jobIndicator;

	@Column(name = "deptid")
	private String deptId;
}
