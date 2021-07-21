package com.fsoft.ez.entity.custom;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class EZN203N03 {
    
    @Column(name = "group_name")
    private String groupName;
    
    @Column(name = "author_id")
    private String authorId;
    
    @Id
    @Column(name = "account")
    private String account;
    
    @Column(name = "type")
    private Integer type;
    
    @Column(name = "descrshort")
    private String descrshort;
    
    @Column(name = "title")
    private String title;
    
    @Column(name = "content")
    private String content;
    
    @Column(name = "hashtag_id")
    private String hashtagId;
    
    @Column(name = "like_count")
    private Integer likeCount;
    
    @Column(name = "attachment")
    private String attachment;
    
    @Column(name = "question")
    private String question;
    
    @Column(name = "thumbnail_url")
    private String thumbnailUrl;
    
    @Column(name = "personal_name")
    private String personalName;
    
    @Column(name = "total_views")
    private String totalViews;
    
}