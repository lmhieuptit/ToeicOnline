package com.fsoft.ez.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fsoft.ez.entity.custom.EZN103N01;

public interface EZN103N01Repository extends JpaRepository<EZN103N01, Long> {

    @Query(nativeQuery = true, value = "SELECT distinct n.news_id, n.title, n.type , e.account, d.descr , n.create_date , n.status "
            + "FROM news n INNER JOIN tbl_department d INNER JOIN tbl_employee e "
            + "WHERE n.delete_flag = false AND n.author_id = e.emplid AND n.department_id = d.deptid AND n.type < 2 "
            + "AND n.draft_flag = 0 ORDER BY n.create_date DESC")
    List<EZN103N01> getAllNewsAdmin();

    @Query(nativeQuery = true, value = "SELECT distinct n.news_id, n.title, n.type , e.account, d.descr , n.create_date , n.status "
            + "FROM news n INNER JOIN tbl_department d INNER JOIN tbl_employee e "
            + "WHERE n.process_id =:processId AND n.delete_flag = false AND n.author_id = e.emplid AND n.department_id = d.deptid AND n.type < 2 "
            + "AND n.draft_flag = 0 ORDER BY n.create_date DESC")
    List<EZN103N01> getAllNewsApprover(@Param("processId") Long processId);
}
