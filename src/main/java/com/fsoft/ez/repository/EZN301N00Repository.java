package com.fsoft.ez.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fsoft.ez.model.response.EZN301N00Response;

@Repository
public interface EZN301N00Repository
        extends JpaRepository<EZN301N00Response, Long> {
    @Query(nativeQuery = true, value = "SELECT n.notification_id, n.content, n.create_date FROM notification n ORDER BY n.create_date DESC")
    List<EZN301N00Response> getAllNotification();
}
