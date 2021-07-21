package com.fsoft.ez.entity.custom;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
@Data
@Entity
public class EZN210 {
    @Id
    @Column(name = "emplid")
    private String emplid;

    @Column(name = "name_display")
    private String nameDisplay;

    @Column(name = "job_indicator")
    private String jobIndicator;

    @Column(name = "descr")
    private String descr;
    
    @Column(name = "account")
    private String account;
}
