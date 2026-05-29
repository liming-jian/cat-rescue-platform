package com.miaoxing.web.controller;

import com.miaoxing.business.entity.SystemNotification;
import com.miaoxing.business.service.SystemNotificationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NotificationController {

    private final SystemNotificationService systemNotificationService;

    public NotificationController(SystemNotificationService systemNotificationService) {
        this.systemNotificationService = systemNotificationService;
    }

    @GetMapping("/notifications")
    public List<SystemNotification> notifications() {
        return systemNotificationService.findLatest(10);
    }
}
