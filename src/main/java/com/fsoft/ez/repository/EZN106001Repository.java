package com.fsoft.ez.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fsoft.ez.entity.custom.EZN106001;

@Repository
public interface EZN106001Repository extends JpaRepository<EZN106001, String> {

	@Query(value = "select title, content, n.author_id as authorid, d.descr, (select e.account from tbl_employee e where e.emplid = n.author_id) as author ,n.type, n.create_date, n.status,\r\n" + 
			"(select e.account from tbl_employee e where e.emplid =:emplId ) as acc, n.approve_date, n.attachment, n.question from news n \r\n" + 
			"left join tbl_department d on n.department_id = d.deptid left join tbl_employee e on e.emplid = n.approver_id where n.news_id =:newsId ", nativeQuery = true)
	EZN106001 findNewsById(@Param("emplId") String emplId, @Param("newsId") Long newsId);
}