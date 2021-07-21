package com.fsoft.ez.model.response;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class EZN211N03Response {
    @Id
    @Column(name = "news_id")
    private Long newsId;
    
    @Column(name = "account")
    private String account;
    
    @Column(name = "thumbnail_url")
    private String thumbnailUrl;
    
    @Column(name = "create_date")
    private LocalDateTime createDate;
    
    @Column(name = "content")
    private String content;
    
    @Column(name = "descr")
    private String descr;
}
