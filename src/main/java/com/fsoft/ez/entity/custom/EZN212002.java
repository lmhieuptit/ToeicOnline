package com.fsoft.ez.entity.custom;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class EZN212002 {

	@Id
	@Column(name="deptid")
	private String deptid;
	
	@Column(name="descr")
	String descr;
	
	@Column(name="countmember")
	Long countMember;
	
	@Column(name="cover_image")
	String coverImage;
}
