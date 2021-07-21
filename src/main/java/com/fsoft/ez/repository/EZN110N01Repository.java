package com.fsoft.ez.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fsoft.ez.common.model.response.EZN110N01ResponseDTO;

@Repository
public interface EZN110N01Repository
        extends JpaRepository<EZN110N01ResponseDTO, Long> {

    @Query(nativeQuery = true, value = "SELECT DISTINCT c.category_id,c.category_name,e.account,c.create_date"
            + " FROM category c INNER JOIN tbl_employee e "
            + "WHERE delete_flag = false AND c.author_id = e.emplid ORDER BY c.create_date DESC ")
    List<EZN110N01ResponseDTO> getAllCategory();
}
