package com.fsoft.ez.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fsoft.ez.constant.Constants;
import com.fsoft.ez.service.NotificationService;

@RestController
@RequestMapping("/api")
public class NotificationController {
	@Autowired
	NotificationService notificationService;

//    @GetMapping("/get-notification-new-feed")
//    List<Notification> getAll() {
//        return this.notificationService.getAllNotification();
//
//    }
//
//    @PostMapping("/save-infomation")
//    String saveNotifications(@RequestParam Long id) {
//        this.notificationService.saveNotifications(id);
//        return Constants.SUCCESS_MSG;
//
//    }

	@PutMapping("/seen-notification")
	public String seenNotification(@RequestParam("notificationid") Long notificationid) {
		this.notificationService.seenNotification(notificationid);
		return Constants.SUCCESS_MSG;
	}
}
