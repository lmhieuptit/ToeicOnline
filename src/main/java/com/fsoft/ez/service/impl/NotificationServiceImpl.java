package com.fsoft.ez.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsoft.ez.entity.Notification;
import com.fsoft.ez.repository.NewsRepository;
import com.fsoft.ez.repository.NotificationRepository;
import com.fsoft.ez.service.NotificationService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	private NewsRepository newsRepository;

	@Autowired
	private NotificationRepository notificationRipository;

	// @Override
	// public String createNotifications(NotificationRequest request) {
	//
	// return null;
	// }

	@Override
	public List<Notification> getAllNotification() {
		return this.notificationRipository.getAll();
	}

	@Override
	public void seenNotification(Long notificationid) {
		Notification notification = this.notificationRipository.findNotiById(notificationid);

		String isSeenList = notification.getIsSeen();
		log.info("notification.getIsSeen();: {}", isSeenList);

	}

	// @Override
	// public void saveNotifications(Long id) {
	// // Notification n = this.notificationRipository.findNotiById(id);
	//
	// List<String> a = new ArrayList<String>();
	// a.add("a");
	// a.add("b");
	// a.add("c");
	// String json = null;
	// ObjectMapper objectMapper = new ObjectMapper();
	// try {
	// json = objectMapper.writerWithDefaultPrettyPrinter()
	// .writeValueAsString(a);
	// System.out.println(json);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// Notification noti = new Notification();
	// noti.setToUser(json);
	//
	// this.notificationRipository.save(noti);
	// }

}
