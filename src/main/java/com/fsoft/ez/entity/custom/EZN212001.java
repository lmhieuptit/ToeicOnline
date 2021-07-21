package com.fsoft.ez.entity.custom;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class EZN212001 {

	@Id
	@Column(name = "news_id")
	private String newsId;

	@Column(name = "title")
	private String title;

	@Column(name = "create_date")
	private String createDate;

	@Column(name = "approve_date")
	private String approveDate;
	
}
