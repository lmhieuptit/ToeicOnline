package com.fsoft.ez.model.response;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class EZN301N00Response {
    @Id
    @Column(name = "notification_id")
    private Long notificationId;
    
    @Column(name = "content")
    private String content;

    @Column(name = "create_date")
    private LocalDateTime createDate;
    
}
