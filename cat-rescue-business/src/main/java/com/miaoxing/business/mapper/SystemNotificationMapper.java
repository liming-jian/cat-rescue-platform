package com.miaoxing.business.mapper;

import com.miaoxing.business.entity.SystemNotification;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SystemNotificationMapper {

    List<SystemNotification> findLatest(@Param("limit") int limit);

    int insert(@Param("message") String message, @Param("target") String target);
}
