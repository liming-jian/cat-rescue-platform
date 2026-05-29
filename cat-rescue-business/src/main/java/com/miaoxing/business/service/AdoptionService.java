package com.miaoxing.business.service;

import com.miaoxing.business.entity.Adoption;
import com.miaoxing.business.entity.view.AdoptionView;

import java.util.List;

public interface AdoptionService {

    boolean add(Adoption adoption);

    List<AdoptionView> findAll();

    boolean deleteById(Integer id);
}
