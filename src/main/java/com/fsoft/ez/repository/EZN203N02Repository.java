package com.fsoft.ez.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fsoft.ez.model.response.EZN203N02Response;

public interface EZN203N02Repository extends JpaRepository<EZN203N02Response, Long> {

	@Query(nativeQuery = true, value = "SELECT n.news_id, n.title, n.create_date, n.approve_date FROM news n "
			+ " LEFT JOIN notification noti on n.news_id = noti.news_id  "
			+ " WHERE n.group_id IS NULL AND n.personal_id IS NULL AND n.type = 0 AND draft_flag = 0 "
			+ " ORDER BY n.approve_date DESC LIMIT 5")
	List<EZN203N02Response> getNewNotifications();
}
