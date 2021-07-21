package com.fsoft.ez.model.response;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class NotificationResponse {
    
    @Id
    @Column(name = "notification_id")
    private Long notificationId;
    
    private String authorId;
    /**
     * 0: thông báo, 1: tin thường, 2: chuc mung sinh nhat
     */
    @Column(name = "type")
    private Integer type;
   
    @Column(name = "personal_id")
    private Long personalId;
    
    @Column(name = "thumbnail_url")
    private String thumbnailUrl;
    
    @Column(name = "group_name")
    private String groupName;
}
