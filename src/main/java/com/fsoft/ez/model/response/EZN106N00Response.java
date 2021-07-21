package com.fsoft.ez.model.response;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class EZN106N00Response {

	private String title;

	private String content;
	
	private String question;

	private String fileContent;
	
	private String author;
	
	private String descr;

	private Integer type;

	private LocalDateTime createDate;

	private Integer status;

	private String account;

	private LocalDateTime approveDate;
	
	private List<String> listVoteOption;
	
	private List<String> listHagTag;
}
