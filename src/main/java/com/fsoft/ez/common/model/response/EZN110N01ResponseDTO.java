package com.fsoft.ez.common.model.response;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Service;
import lombok.Data;


@Data
@Entity
public class EZN110N01ResponseDTO {

    @Id
    @Column(name = "category_id")
    private Long categoryId;
    
    @Column(name = "category_name")
    private String categoryName;
    
    @Column(name = "account")
    private String account;
    
    @Column(name = "create_date")
    private LocalDateTime createDate;
}
