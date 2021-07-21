/**
 * Copyright © 2020 Suntory Corporation. All Rights Reserved.
 **/
package com.fsoft.ez.entity;

import java.io.Serializable;
import java.time.LocalDate;
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
@Table(name = "news")
public class News implements Serializable {
    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "news_id", nullable = false)
    private Long newsId;

    @Column(name = "department_id", nullable = true)
    private Long departmentId;

    @Column(name = "process_id", nullable = false)
    private Long processId;

    @Column(name = "approve_date", nullable = true)
    private LocalDateTime approveDate;

    @Column(name = "category_id", nullable = true)
    private Long categoryId;

    @Column(name = "author_id", nullable = false)
    private Long authorId;

    @Column(name = "personal_id", nullable = true)
    private Long personalId;

    @Column(name = "group_id", nullable = true)
    private Long groupId;

    @Column(name = "reason_reject", nullable = true)
    private String reasonReject;

    /**
     * chuc mung sinh nhat, question cho truong hop vote, title cho truong hop bai news
     */
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "question", nullable = true)
    private String question;

    @Column(name = "question_end_date", nullable = true)
    private LocalDate questionEndDate;

    @Column(name = "number_of_choice", nullable = true)
    private Long numberOfChoice;

    @Column(name = "content", nullable = true)
    private String content;

    @Column(name = "attachment", nullable = true)
    private String attachment;
    
    /**
     * 0: thông báo, 1: tin thường, 2: chuc mung sinh nhat
     */
    @Column(name = "type", nullable = true)
    private Integer type;

    /**
     * 0: chờ duyệt, 1: đã duyệt, 2: từ chối
     */
    @Column(name = "status", nullable = true)
    private Integer status;

    @Column(name = "thumbnail_original_name", nullable = true)
    private String thumbnailOriginalName;
    
    @Column(name = "thumbnail_url", nullable = true)
    private String thumbnailUrl;

    /**
     * 1: là bản nháp, 0: ngược lại
     */
    @Column(name = "draft_flag", nullable = true, columnDefinition = "TINYINT(1)")
    private Boolean draftFlag;

    @Column(name = "total_views", nullable = true)
    private Long totalViews;

    @Column(name = "like_id", nullable = true)
    private String likeId;

    @Column(name = "create_date", nullable = true)
    private LocalDateTime createDate;

    @Column(name = "update_date", nullable = true)
    private LocalDateTime updateDate;
    
    @Column(name = "approver_id", nullable = true)
    private String approverId;

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