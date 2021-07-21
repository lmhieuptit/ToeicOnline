package com.fsoft.ez.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fsoft.ez.entity.custom.EZN10101;

@Repository
public interface EZN101001Repository extends JpaRepository<EZN10101, Long> {

	@Query(nativeQuery = true, value = "select p.process_id,  "
			+ "p.process_name, p.approver_id , e.account , p.job_indicator , p.create_date, p.status, d.deptid , d.descr, d.full_path from process p "
			+ "left join tbl_employee e on p.approver_id=e.emplid join tbl_department d on p.department_id=d.deptid "
			+ "where p.process_name like :keySearchProcess% order by p.create_date desc")
	List<EZN10101> findProcessByName(@Param("keySearchProcess") String keySearchProcess);

}
