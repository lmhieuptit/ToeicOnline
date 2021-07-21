package com.fsoft.ez.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fsoft.ez.model.response.EZN210N002Response;

@Repository
public interface EZN210N03Repository
        extends JpaRepository<EZN210N002Response, Long> {
    @Query(nativeQuery = true, value = "select * from news")
    List<EZN210N002Response> getNews();
}
