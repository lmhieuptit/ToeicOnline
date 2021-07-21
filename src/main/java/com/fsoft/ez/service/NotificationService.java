package com.fsoft.ez.service;

import java.util.List;

import com.fsoft.ez.entity.Notification;

public interface NotificationService {

	List<Notification> getAllNotification();

	void seenNotification(Long notificationid);

}
