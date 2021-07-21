package com.fsoft.ez.model.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.persistence.Column;

import org.springframework.web.multipart.MultipartFile;

import com.fsoft.ez.dto.UserInfoDTO;

import lombok.Data;

@Data
public class EZN105N00Response {

	private String title;

	private String content;

	private String author;

	private Integer type;

	private String fileContent;
	
	private String descr;
	
	private LocalDateTime createDate;

	private Integer status;

	private String account;

	private String jobIndicator;

	private LocalDateTime approveDate;
	
	private String question;
	
	private String reasonReject;
	
	private List<String> listOption;
	
	private List<String> listHashTag;
	
	private Optional<UserInfoDTO> approverInfo;
	
}
