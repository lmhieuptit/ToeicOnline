package com.fsoft.ez.model.response;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class EZN210N002Response {

	@Id
	@Column(name = "group_id")
	private Long groupId;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "name_display")
	private String nameDisplay;

	@Column(name = "avatar_url")
	private String avatarUrl;

	private String department;

}
