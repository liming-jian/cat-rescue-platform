package com.miaoxing.business.mapper;

import com.miaoxing.business.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    User findByUsername(@Param("username") String username);

    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    int insertUser(User user);

    int updatePassword(@Param("username") String username, @Param("password") String password);
}
