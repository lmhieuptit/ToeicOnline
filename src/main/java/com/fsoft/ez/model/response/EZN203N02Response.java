package com.fsoft.ez.model.response;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class EZN203N02Response {
    
    @Id
    @Column(name = "news_id")
    private Long newsId;
    
    /**
     * chuc mung sinh nhat, question cho truong hop vote, title cho truong hop bai news
     */
    @Column(name = "title")
    private String title;
    
    @Column(name = "create_date")
    private LocalDateTime createDate;
    
	@Column(name = "approve_date")
	private String approveDate;
}
