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
@Table(name = "tbl_department")
public class TblDepartment implements Serializable {
    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "setid", nullable = true)
    private String setid;

    @Column(name = "parent_dept", nullable = true)
    private String parentDept;

    @Column(name = "deptid", nullable = true)
    private String deptid;

    @Column(name = "eff_status", nullable = true)
    private String effStatus;

    @Column(name = "descr", nullable = true)
    private String descr;

    @Column(name = "full_path", nullable = true)
    private String fullPath;

    @Column(name = "descrshort", nullable = true)
    private String descrshort;

    @Column(name = "manager_id", nullable = true)
    private String managerId;
    
    @Column(name = "cover_image", nullable = true)
    private String coverImage;

    @Column(name = "created_time", nullable = true)
    private LocalDateTime createdTime;

    @Column(name = "modified_time", nullable = true)
    private LocalDateTime modifiedTime;

    @Column(name = "created_by", nullable = true)
    private String createdBy;

    @Column(name = "modified_by", nullable = true)
    private String modifiedBy;
}