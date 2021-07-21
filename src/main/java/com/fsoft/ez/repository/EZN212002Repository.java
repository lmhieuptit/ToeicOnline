package com.fsoft.ez.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fsoft.ez.entity.custom.EZN212002;

@Repository
public interface EZN212002Repository extends JpaRepository<EZN212002, String>{

	@Query(nativeQuery = true, value = "select td.deptid, td.descr, count(emplid) as countmember,td.cover_image "
			+ "from tbl_department td join tbl_employee te on td.deptid = te.company where te.company =:deptId group by td.deptid")
	EZN212002 getCompanyDetail(@Param("deptId") String deptId);
}
