package com.fsoft.ez.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fsoft.ez.model.response.EZN211N03Response;

@Repository
public interface EZN211N03Repository
        extends JpaRepository<EZN211N03Response, Long> {

    @Query(nativeQuery = true, value = "SELECT DISTINCT n.news_id,n.create_date, n.thumbnail_url, n.content , e.account, d.descr "
            + "FROM news n INNER JOIN tbl_employee e INNER JOIN tbl_department d "
            + "WHERE n.group_id =:groupId AND n.delete_flag = 0 AND n.status = 0 "
            + "AND n.author_id = e.emplid AND e.deptid = d.deptid and n.draft_flag = 0 "
            + "ORDER BY n.create_date DESC")
    List<EZN211N03Response> getAllNewUnapproved(@Param("groupId") Long groupId);
}
