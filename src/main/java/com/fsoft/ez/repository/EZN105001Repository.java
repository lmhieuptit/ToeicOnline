package com.fsoft.ez.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fsoft.ez.entity.custom.EZN105001;

@Repository
public interface EZN105001Repository extends JpaRepository<EZN105001, String> {

	@Query(value = "select title, content, n.author_id as authorid, d.descr, (select account from tbl_employee e "
			+ "where e.emplid=authorid) as author, n.type, n.create_date, n.status, e.account, p.job_indicator, n.approve_date, n.attachment, n.question, n.reason_reject, n.approver_id from news n left join process "
			+ "p on n.process_id= p.process_id left join tbl_department d on p.department_id = d.deptid left join tbl_employee e on p.approver_id=e.emplid where n.news_id =:newsId and n.delete_flag = 0 and n.draft_flag = 0", nativeQuery = true)
	EZN105001 findNewsById(@Param("newsId") Long newsId);

}