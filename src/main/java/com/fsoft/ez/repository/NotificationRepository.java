package com.fsoft.ez.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fsoft.ez.entity.Notification;

@Repository
public interface NotificationRepository
        extends JpaRepository<Notification, Long> {

    @Query("select n from Notification n")
    List<Notification> getAll();

    @Query("select n from Notification n where n.notificationId =:id")
    Notification findNotiById(@Param("id") Long id);

}
