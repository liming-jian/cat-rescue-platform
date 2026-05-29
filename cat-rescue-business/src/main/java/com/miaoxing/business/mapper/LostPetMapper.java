package com.miaoxing.business.mapper;

import com.miaoxing.business.entity.LostPet;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LostPetMapper {

    List<LostPet> findAll();

    int insert(LostPet lostPet);

    int updateStatus(@Param("id") Integer id, @Param("status") String status);

    int deleteById(@Param("id") Integer id);
}
