package com.fsoft.ez.model.response;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class EZN211N02Response {
    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "account")
    private String account;

    @Column(name = "descr")
    private String descr;
    
    @Column(name = "job_indicator")
    private String jobIndicator;

    /**
     * 0: member, 1: admin
     */
    @Column(name = "role_group")
    private Integer roleGroup;
    
    @Column(name = "descrshort")
	private String descrshort;
    
    @Column(name = "avatar_url")
    private String avatarUrl;

}
