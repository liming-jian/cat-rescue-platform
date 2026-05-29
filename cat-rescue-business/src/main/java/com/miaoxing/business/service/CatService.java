package com.miaoxing.business.service;

import com.miaoxing.business.entity.Cat;

import java.util.List;

public interface CatService {

    List<Cat> findAll();

    List<Cat> findByConditions(String status, String gender, String ageKeyword, String nameKeyword);

    boolean add(Cat cat);

    boolean updateStatus(Integer id, String status);

    boolean deleteById(Integer id);
}
