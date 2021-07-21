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
@Table(name = "hashtag")
public class Hashtag implements Serializable {
    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hashtag_id", nullable = false)
    private Long hashtagId;

    @Column(name = "hashtag_name", nullable = true)
    private String hashtagName;

    @Column(name = "create_date", nullable = true)
    private LocalDateTime createDate;

    @Column(name = "update_date", nullable = true)
    private LocalDateTime updateDate;

    /**
     * 0: not delete, 1: delete
     */
    @Column(name = "delete_flag", nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean deleteFlag;

    @PrePersist
    protected void onCreate() {
        this.createDate = this.updateDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updateDate = LocalDateTime.now();
    }
}