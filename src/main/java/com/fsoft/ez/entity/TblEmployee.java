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
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
@Table(name = "tbl_employee")
public class TblEmployee implements Serializable {
    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "emplid", nullable = true)
    private String emplid;

    @Column(name = "deptid", nullable = true)
    private String deptid;

    @Column(name = "company", nullable = true)
    private String company;

    @Column(name = "account", nullable = true)
    private String account;

    @Column(name = "email_addr", nullable = true)
    private String emailAddr;
    
    @Column(name = "address",nullable = true)
    private String address;
    
    @Column(name = "phone_number",nullable = true)
    private String phoneNumber;

    @Column(name = "lower_email_addr", nullable = true)
    private String lowerEmailAddr;

    @Column(name = "name_display", nullable = true)
    private String nameDisplay;

    @Column(name = "hr_status", nullable = true)
    private String hrStatus;

    @Column(name = "location", nullable = true)
    private String location;

    @Column(name = "sex", nullable = true)
    private String sex;

    @Column(name = "jobcode", nullable = true)
    private String jobcode;

    @Column(name = "setid_jobcode", nullable = true)
    private String setidJobcode;

    @Column(name = "setid_location", nullable = true)
    private String setidLocation;

    @Column(name = "contract_type", nullable = true)
    private String contractType;

    @Column(name = "created_time", nullable = true)
    private LocalDateTime createdTime;

    @Column(name = "empl_rcd", nullable = true)
    private String emplRcd;

    @Column(name = "modified_time", nullable = true)
    private LocalDateTime modifiedTime;

    @Column(name = "created_by", nullable = true)
    private String createdBy;

    @Column(name = "modified_by", nullable = true)
    private String modifiedBy;

    @Column(name = "job_indicator", nullable = true)
    private String jobIndicator;

    @Column(name = "type", nullable = true)
    private String type;

    @Column(name = "tenant_id", nullable = true)
    private String tenantId;

    @Column(name = "date_of_birth", nullable = true)
    private LocalDate dateOfBirth;

    @Column(name = "avatar_url", nullable = true)
    private String avatarUrl;
    
    @Column(name = "cover_image", nullable = true)
    private String coverImage;
}