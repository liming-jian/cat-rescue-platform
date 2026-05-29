package com.miaoxing.business.service.impl;

import com.miaoxing.business.entity.Adoption;
import com.miaoxing.business.entity.view.AdoptionView;
import com.miaoxing.business.mapper.AdoptionMapper;
import com.miaoxing.business.service.AdoptionService;
import com.miaoxing.business.service.SystemNotificationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdoptionServiceImpl implements AdoptionService {

    private final AdoptionMapper adoptionMapper;
    private final SystemNotificationService systemNotificationService;

    public AdoptionServiceImpl(AdoptionMapper adoptionMapper, SystemNotificationService systemNotificationService) {
        this.adoptionMapper = adoptionMapper;
        this.systemNotificationService = systemNotificationService;
    }

    @Override
    public boolean add(Adoption adoption) {
        boolean success = adoptionMapper.insert(adoption) > 0;
        if (success) {
            systemNotificationService.send("📝 新领养申请：" + adoption.getApplicantName(), "admin");
        }
        return success;
    }

    @Override
    public List<AdoptionView> findAll() {
        return adoptionMapper.findAllWithCatStatus();
    }

    @Override
    public boolean deleteById(Integer id) {
        return adoptionMapper.deleteById(id) > 0;
    }
}
