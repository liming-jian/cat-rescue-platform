package com.miaoxing.business.service.impl;

import com.miaoxing.business.entity.LostPet;
import com.miaoxing.business.mapper.LostPetMapper;
import com.miaoxing.business.service.LostPetService;
import com.miaoxing.business.service.SystemNotificationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LostPetServiceImpl implements LostPetService {

    private final LostPetMapper lostPetMapper;
    private final SystemNotificationService systemNotificationService;

    public LostPetServiceImpl(LostPetMapper lostPetMapper, SystemNotificationService systemNotificationService) {
        this.lostPetMapper = lostPetMapper;
        this.systemNotificationService = systemNotificationService;
    }

    @Override
    public List<LostPet> findAll() {
        return lostPetMapper.findAll();
    }

    @Override
    public boolean add(LostPet lostPet) {
        boolean success = lostPetMapper.insert(lostPet) > 0;
        if (success) {
            systemNotificationService.send("🆘 互助信息更新：" + lostPet.getPetName(), "all");
        }
        return success;
    }

    @Override
    public boolean updateStatus(Integer id, String status) {
        boolean success = lostPetMapper.updateStatus(id, status) > 0;
        if (success) {
            systemNotificationService.send("🎉 好消息：走失的宠物已经回家了！", "all");
        }
        return success;
    }

    @Override
    public boolean deleteById(Integer id) {
        boolean success = lostPetMapper.deleteById(id) > 0;
        if (success) {
            systemNotificationService.send("ℹ️ 管理员更新了寻猫/招领信息", "all");
        }
        return success;
    }
}
