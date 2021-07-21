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
@Table(name = "comment")
public class Comment implements Serializable {
    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", nullable = false)
    private Long commentId;

    @Column(name = "news_id", nullable = false)
    private Long newsId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "parent_comment_id", nullable = true)
    private Long parentCommentId;

    /**
     * 0: not parent comment, 1: parent comment
     */
    @Column(name = "is_parent_comment", nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean isParentComment = false;

    @Column(name = "like_id", nullable = true)
    private String likeId;

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