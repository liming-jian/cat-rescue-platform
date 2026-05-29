package com.miaoxing.business.service;

import com.miaoxing.business.entity.User;

public interface UserService {

    User login(String username, String password);

    User findByUsername(String username);

    boolean register(String username, String password);

    boolean resetPassword(String username, String newPassword);
}
