package com.fsoft.ez.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fsoft.ez.model.response.EZN210N003Response;

@Repository
public interface EZN210N003Repository
        extends JpaRepository<EZN210N003Response, Long> {
    @Query(nativeQuery = true, value = "SELECT notifi.notification_id, notifi.content ,notifi.create_date FROM notification notifi JOIN notification_user notifiUser "
            + "WHERE notifi.group_id =:groupId "
            + "AND notifi.notification_id = notifiUser.notification_id "
            + "AND notifiUser.is_seen = 0 "
            + "ORDER BY notifi.create_date LIMIT 10 ;")
    List<EZN210N003Response> getNotifications(@Param("groupId") Long groupId);
}
