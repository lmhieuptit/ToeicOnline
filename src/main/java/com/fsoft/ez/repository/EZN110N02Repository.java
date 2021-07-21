package com.fsoft.ez.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fsoft.ez.model.response.EZN110N00Response;

@Repository
public interface EZN110N02Repository
        extends JpaRepository<EZN110N00Response, Long> {

    @Query(nativeQuery = true, value = "SELECT DISTINCT c.category_id, c.category_name, c.create_date, e.account "
            + "FROM category c INNER JOIN tbl_employee e "
            + "WHERE c.category_name like :name AND c.delete_flag = false AND c.author_id = e.emplid "
            + "ORDER BY c.create_date DESC")
    List<EZN110N00Response> getNewsbyName(@Param("name") String name);
}
