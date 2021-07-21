package com.fsoft.ez.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fsoft.ez.entity.Notification;
import com.fsoft.ez.service.NotificationService;

@RestController
@RequestMapping("/api")
public class EZN301Controller {

    private NotificationService notificationService;

    @Autowired
    public EZN301Controller(NotificationService notificationService) {
        super();
        this.notificationService = notificationService;
    }

    @GetMapping("/notifications")
    public List<Notification> getNotifications() {
        return this.notificationService.getAllNotification();
    }
}
