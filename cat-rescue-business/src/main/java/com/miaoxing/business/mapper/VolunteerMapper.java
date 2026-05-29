package com.miaoxing.business.mapper;

import com.miaoxing.business.entity.Volunteer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VolunteerMapper {

    int insert(Volunteer volunteer);

    List<Volunteer> findAll();

    int deleteById(@Param("id") Integer id);
}
