package com.miaoxing.business.service.impl;

import com.miaoxing.business.entity.Cat;
import com.miaoxing.business.mapper.CatMapper;
import com.miaoxing.business.service.CatService;
import com.miaoxing.business.service.SystemNotificationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatServiceImpl implements CatService {

    private final CatMapper catMapper;
    private final SystemNotificationService systemNotificationService;

    public CatServiceImpl(CatMapper catMapper, SystemNotificationService systemNotificationService) {
        this.catMapper = catMapper;
        this.systemNotificationService = systemNotificationService;
    }

    @Override
    public List<Cat> findAll() {
        return catMapper.findAll();
    }

    @Override
    public List<Cat> findByConditions(String status, String gender, String ageKeyword, String nameKeyword) {
        return catMapper.findByConditions(status, gender, ageKeyword, nameKeyword);
    }

    @Override
    public boolean add(Cat cat) {
        boolean success = catMapper.insert(cat) > 0;
        if (success) {
            systemNotificationService.send("😻 新猫上架：" + cat.getName() + " 正在寻找新家！", "all");
        }
        return success;
    }

    @Override
    public boolean updateStatus(Integer id, String status) {
        boolean success = catMapper.updateStatus(id, status) > 0;
        if (success) {
            systemNotificationService.send("🏠 喜讯：猫咪已被成功领养！", "all");
        }
        return success;
    }

    @Override
    public boolean deleteById(Integer id) {
        boolean success = catMapper.deleteById(id) > 0;
        if (success) {
            systemNotificationService.send("ℹ️ 管理员更新了猫咪库", "all");
        }
        return success;
    }
}
