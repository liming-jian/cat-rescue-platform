package com.miaoxing.business.service.impl;

import com.miaoxing.business.entity.User;
import com.miaoxing.business.mapper.UserMapper;
import com.miaoxing.business.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User login(String username, String password) {
        return userMapper.findByUsernameAndPassword(username, password);
    }

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public boolean register(String username, String password) {
        User existing = userMapper.findByUsername(username);
        if (existing != null) {
            return false;
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole("user");
        return userMapper.insertUser(user) > 0;
    }

    @Override
    public boolean resetPassword(String username, String newPassword) {
        return userMapper.updatePassword(username, newPassword) > 0;
    }
}
