package com.fsoft.ez.entity.custom;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
@Data
@Entity
public class EZN210N01 {
    @Id
    @Column(name = "user_id")
    private String emplid;

    @Column(name = "account")
    private String account;

    @Column(name = "descrshort")
    private String descrshort;
}
