package com.fsoft.ez.entity.custom;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class EZN206001 {

	@Id
	@Column(name="emplid")
	private String emplId;
	
	@Column(name = "account")
	private String account;
	
	@Column(name = "name_display")
	private String nameDisplay;
	
}
