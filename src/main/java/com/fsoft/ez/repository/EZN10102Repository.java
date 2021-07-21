package com.fsoft.ez.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fsoft.ez.entity.custom.EZN10102;

@Repository
public interface EZN10102Repository extends JpaRepository<EZN10102, String> {

	@Query(value = "Select DISTINCT e.deptid, e.job_indicator from tbl_department d join tbl_employee e on d.deptid = e.deptid "
			+ "where d.deptid =:deptId and e.job_indicator IS NOT NULL", nativeQuery = true)
	List<EZN10102> findJobIndecatorByDeptId(@Param("deptId") String deptId);
	
	@Query(value = "select count(department_id) from process p where p.department_id =:deptId", nativeQuery = true)
	int countDepartment(@Param("deptId") String deptId);
}
