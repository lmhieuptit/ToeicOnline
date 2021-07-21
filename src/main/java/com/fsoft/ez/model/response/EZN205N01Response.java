package com.fsoft.ez.model.response;

import java.time.LocalDateTime;

import javax.persistence.Entity;

import lombok.Data;

@Data
public class EZN205N01Response {

	 	private Long newsId;
	 	
	    private String title;
	    
	    private Integer type;
	    
	    private LocalDateTime createDate;
	    
	    /**
	     * 0 : chờ duyệt 
	     * 1 : đã duyệt
	     * 2 : từ chối
	     * 3 : tin nháp
	     */
	    private Integer status;
}
