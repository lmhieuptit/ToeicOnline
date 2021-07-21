package com.fsoft.ez.entity.custom;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class EZN211003 {

	@Id
	@Column(name = "emplid")
	private String emplId;

	@Column(name = "account")
	private String account;

	@Column(name = "descr")
	private String departmentName;

	@Column(name = "avatar_url")
	private String avatarUrl;

	@Column(name = "descrshort")
	private String descrshort;
}
