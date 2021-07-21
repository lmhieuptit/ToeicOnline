package com.fsoft.ez.entity.custom;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class EZN106001 {

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

	@Column(name = "acc")
	private String account;

	@Column(name = "approve_date")
	private LocalDateTime approveDate;
}
