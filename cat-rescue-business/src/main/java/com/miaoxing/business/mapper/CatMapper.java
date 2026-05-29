package com.miaoxing.business.mapper;

import com.miaoxing.business.entity.Cat;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CatMapper {

    List<Cat> findAll();

    List<Cat> findByConditions(@Param("status") String status,
                               @Param("gender") String gender,
                               @Param("ageKeyword") String ageKeyword,
                               @Param("nameKeyword") String nameKeyword);

    int insert(Cat cat);

    int updateStatus(@Param("id") Integer id, @Param("status") String status);

    int deleteById(@Param("id") Integer id);
}
