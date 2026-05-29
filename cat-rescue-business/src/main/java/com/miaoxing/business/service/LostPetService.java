package com.miaoxing.business.service;

import com.miaoxing.business.entity.LostPet;

import java.util.List;

public interface LostPetService {

    List<LostPet> findAll();

    boolean add(LostPet lostPet);

    boolean updateStatus(Integer id, String status);

    boolean deleteById(Integer id);
}
