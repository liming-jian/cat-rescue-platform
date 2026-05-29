package com.miaoxing.business.mapper;

import com.miaoxing.business.entity.Adoption;
import com.miaoxing.business.entity.view.AdoptionView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdoptionMapper {

    int insert(Adoption adoption);

    List<AdoptionView> findAllWithCatStatus();

    int deleteById(@Param("id") Integer id);
}
