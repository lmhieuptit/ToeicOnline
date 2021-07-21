package com.fsoft.ez.model.response;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
public class EZN203N03Response {
    
    private String groupName;
    
    private String authorId;
    
    private String account;
    
    private Integer type;
    
    private String descrshort;
    
    private String title;
    
    private String content;
    
    private String hashtagId;
    
    private List<String> hashtag_name;
    
    private Integer likeId;
    
    private List<String> option_answer;
    
    private String attachment;
    
    private String question;
    
    private String thumbnailUrl;
    
    private String personalName;
    
    private String totalViews;
    
}
