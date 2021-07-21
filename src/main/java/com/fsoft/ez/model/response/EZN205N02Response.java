package com.fsoft.ez.model.response;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class EZN205N02Response {
    
    @Id
    @Column(name = "emplid")
    private String emplId;
    
    @Column(name = "email_addr")
    private String email;
    
    @Column(name = "descr")
    private String descr;
    
    @Column(name = "name_display")
    private String nameDisplay;

    @Column(name = "account")
    private String account;
    
    @Column(name = "job_indicator")
    private String jobIndicator;
    
    @Column(name = "phone_number")
    private String phoneNumber;
    
    @Column(name = "address")
    private String address;
    
    @Column(name = "avatar_url")
    private String avatarUrl;
    
    @Column(name = "cover_image")
    private String coverImage;
    
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
}
