package com.fsoft.ez.model.response;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class EZN110N00Response {
    
    @Id
    @Column(name = "category_id")
    private Long categoryId;
    
    @Column(name = "category_name")
    private String categoryName;
    
    @Column(name = "create_date")
    private LocalDateTime createDate;
    
    @Column(name = "account")
    private String account;
}
