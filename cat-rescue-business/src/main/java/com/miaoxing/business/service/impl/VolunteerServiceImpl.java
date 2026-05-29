package com.miaoxing.business.service.impl;

import com.miaoxing.business.entity.Volunteer;
import com.miaoxing.business.mapper.VolunteerMapper;
import com.miaoxing.business.service.SystemNotificationService;
import com.miaoxing.business.service.VolunteerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VolunteerServiceImpl implements VolunteerService {

    private final VolunteerMapper volunteerMapper;
    private final SystemNotificationService systemNotificationService;

    public VolunteerServiceImpl(VolunteerMapper volunteerMapper, SystemNotificationService systemNotificationService) {
        this.volunteerMapper = volunteerMapper;
        this.systemNotificationService = systemNotificationService;
    }

    @Override
    public boolean add(Volunteer volunteer) {
        boolean success = volunteerMapper.insert(volunteer) > 0;
        if (success) {
            systemNotificationService.send("🙋‍♂️ 新志愿者申请：" + volunteer.getName(), "admin");
        }
        return success;
    }

    @Override
    public List<Volunteer> findAll() {
        return volunteerMapper.findAll();
    }

    @Override
    public boolean deleteById(Integer id) {
        return volunteerMapper.deleteById(id) > 0;
    }
}
