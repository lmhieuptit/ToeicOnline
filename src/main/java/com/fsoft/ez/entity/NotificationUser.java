/**
 * Copyright © 2020 Suntory Corporation. All Rights Reserved.
 **/
package com.fsoft.ez.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
@Table(name = "notification_user")
public class NotificationUser implements Serializable {
    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_user_id", nullable = false)
    private Long notificationUserId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "notification_id", nullable = false)
    private Long notificationId;

    /**
     * 0: not seen, 1: seen
     */
    @Column(name = "is_seen", nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean isSeen;

    @Column(name = "create_date", nullable = true)
    private LocalDateTime createDate;

    @Column(name = "update_date", nullable = true)
    private LocalDateTime updateDate;


    @PrePersist
    protected void onCreate() {
        this.createDate = this.updateDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updateDate = LocalDateTime.now();
    }
}