package com.miaoxing.business.service;

import com.miaoxing.business.entity.SystemNotification;

import java.util.List;

public interface SystemNotificationService {

    List<SystemNotification> findLatest(int limit);

    void send(String message, String target);
}
