package com.fsoft.ez.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fsoft.ez.entity.custom.EZN206001;

public interface EZN206001Repository extends JpaRepository<EZN206001, String>{

	@Query(nativeQuery = true, value = "SELECT emplid, account, name_display FROM tbl_employee where emplid <> :emplId")
	List<EZN206001> getAllUser(@Param("emplId") Long emplId);
}
