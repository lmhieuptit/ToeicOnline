package com.fsoft.ez.model.response;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class EZN107N00Response {

	private String title;

	private String content;
	
	private String fileContent;
	
	private String question;

	private String author;
	
	private String descr;

	private Integer type;

	private LocalDateTime createDate;

	private Integer status;

	private String account;

	private String jobIndicator;

	private LocalDateTime approveDate;

	private String reasonReject;
	
	private List<String> listVoteOption;
	
	private List<String> listHagTag;
}
