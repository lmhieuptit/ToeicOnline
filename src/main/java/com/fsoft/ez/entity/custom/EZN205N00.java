package com.fsoft.ez.entity.custom;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class EZN205N00 {
    
    @Id
    @Column(name = "news_id")
    private Long newsId;
    
    /**
     * chuc mung sinh nhat, question cho truong hop vote, title cho truong hop bai news
     */
    @Column(name = "title")
    private String title;
    
    /**
     * 0: thông báo, 1: tin thường, 2: chuc mung sinh nhat
     */
    @Column(name = "type")
    private Integer type;
    
    @Column(name = "create_date")
    private LocalDateTime createDate;
    /**
     * 0: chờ duyệt, 1: đã duyệt, 2: từ chối
     */
    @Column(name = "status", nullable = true)
    private Integer status;
   
    /**
     * 0 : bản nháp
     * 
     */
    @Column(name = "draft_flag")
    private Boolean draftFlag;
}
