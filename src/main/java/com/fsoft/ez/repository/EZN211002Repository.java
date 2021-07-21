package com.fsoft.ez.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fsoft.ez.entity.custom.EZN211002;

public interface EZN211002Repository extends JpaRepository<EZN211002, Long> {

	@Query(value = "select e.emplid, e.account, descr, e.avatar_url, descrshort  from `group` as g inner join group_member gm on g.group_id=gm.group_id "
			+ "join tbl_employee e on gm.user_id=e.id join tbl_department d on e.deptid=d.id where gm.role_group=1 and g.group_id =:groupId", nativeQuery = true)
	List<EZN211002> getListAdmin(@Param("groupId") Long groupId);
}
