package com.fsoft.ez.entity.custom;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class EZN107001 {

	@Id
	@Column(name = "title")
	private String title;

	@Column(name = "content")
	private String content;
	
	@Column(name = "attachment")
	private String attachment;
	
	@Column(name="question")
	private String question;

	@Column(name = "author")
	private String author;
	
	@Column(name = "descr")
	private String desrc;

	@Column(name = "type")
	private Integer type;

	@Column(name = "create_date")
	private LocalDateTime createDate;

	@Column(name = "status")
	private Integer status;

	@Column(name = "account")
	private String account;

	@Column(name = "job_indicator")
	private String jobIndicator;

	@Column(name = "approve_date")
	private LocalDateTime approveDate;

	@Column(name = "reason_reject")
	private String reasonReject;
}
