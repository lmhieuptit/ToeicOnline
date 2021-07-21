/**
 * Copyright © 2020 Suntory Corporation. All Rights Reserved.
 **/
package com.fsoft.ez.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.context.SecurityContextHolder;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "category")
public class Category implements Serializable {
    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @Column(name = "category_name", nullable = true)
    private String categoryName;

    @Column(name = "create_date", nullable = true)
    private LocalDateTime createDate;

    @Column(name = "author_id", nullable = true)
    private String authorId;
    
    @Column(name = "update_date", nullable = true)
    private LocalDateTime updateDate;
    
    @Column(name = "modified_by")
    @LastModifiedBy
    private String modifiedBy;

    /**
     * 0: not delete, 1: delete
     */
    @Column(name = "delete_flag", nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean deleteFlag;

    @PrePersist
    protected void onCreate() {
        this.createDate = this.updateDate = LocalDateTime.now();
        this.authorId = ((TblEmployee) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmplid();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updateDate = LocalDateTime.now();
    }
}