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
@Table(name = "vote_option")
public class VoteOption implements Serializable {
    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vote_option_id", nullable = false)
    private Long voteOptionId;

    @Column(name = "news_id", nullable = false)
    private Long newsId;

    @Column(name = "option_answer", nullable = false)
    private String optionAnswer;

    /**
     * list user id vote for this option, ex: "1,2,3"
     */
    @Column(name = "vote_user_id", nullable = true)
    private String voteUserId;

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