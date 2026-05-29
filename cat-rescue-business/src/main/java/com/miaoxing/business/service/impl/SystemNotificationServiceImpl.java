package com.miaoxing.business.service.impl;

import com.miaoxing.business.entity.SystemNotification;
import com.miaoxing.business.mapper.SystemNotificationMapper;
import com.miaoxing.business.service.SystemNotificationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemNotificationServiceImpl implements SystemNotificationService {

    private final SystemNotificationMapper systemNotificationMapper;

    public SystemNotificationServiceImpl(SystemNotificationMapper systemNotificationMapper) {
        this.systemNotificationMapper = systemNotificationMapper;
    }

    @Override
    public List<SystemNotification> findLatest(int limit) {
        return systemNotificationMapper.findLatest(limit);
    }

    @Override
    public void send(String message, String target) {
        systemNotificationMapper.insert(message, target);
    }
}
