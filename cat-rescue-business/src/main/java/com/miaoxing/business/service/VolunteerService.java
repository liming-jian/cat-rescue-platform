package com.miaoxing.business.service;

import com.miaoxing.business.entity.Volunteer;

import java.util.List;

public interface VolunteerService {

    boolean add(Volunteer volunteer);

    List<Volunteer> findAll();

    boolean deleteById(Integer id);
}
