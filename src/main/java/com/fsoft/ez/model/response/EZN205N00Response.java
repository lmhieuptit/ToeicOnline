package com.fsoft.ez.model.response;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class EZN205N00Response {

	private Long newsId;

	/**
	 * chuc mung sinh nhat, question cho truong hop vote, title cho truong hop bai
	 * news
	 */
	private String title;

	/**
	 * 0: thông báo, 1: tin thường, 2: chuc mung sinh nhat
	 */
	private Integer type;

	private LocalDateTime createDate;
	/**
	 * 0: chờ duyệt, 1: đã duyệt, 2: từ chối
	 */
	private Integer status;

	/**
	 * 0 : bản nháp
	 *
	 */
	private Boolean draftFlag;

	private String wall;

	private Long personalId;

	private Long groupId;
}
