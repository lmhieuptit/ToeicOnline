package com.fsoft.ez.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fsoft.ez.entity.custom.EZN212001;

@Repository
public interface EZN212001Repository extends JpaRepository<EZN212001, Long> {

	@Query(value = "select news_id, title, create_date, approve_date from news n "
			+ "where `type` = 0 and status = 1 order by approve_date desc", nativeQuery = true)
	List<EZN212001> findNewsNotify();

	@Query(value = "select news_id, title, create_date, approve_date from news n "
			+ "where `type` = 0 and status = 1 order by approve_date desc Limit :limit", nativeQuery = true)
	List<EZN212001> findNewsNotifyByLimit(@Param("limit") Long limit);

}
