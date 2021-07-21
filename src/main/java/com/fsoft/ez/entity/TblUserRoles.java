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
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
@Table(name = "tbl_user_roles")
public class TblUserRoles implements Serializable {
    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "code_role", nullable = true)
    private String codeRole;

    @Column(name = "email", nullable = true)
    private String email;

    @Column(name = "company", nullable = true)
    private String company;

    @Column(name = "created_by", nullable = true)
    private String createdBy;

    @Column(name = "created_time", nullable = true)
    private LocalDateTime createdTime;

    @Column(name = "modified_by", nullable = true)
    private String modifiedBy;

    @Column(name = "modified_time", nullable = true)
    private LocalDateTime modifiedTime;
}