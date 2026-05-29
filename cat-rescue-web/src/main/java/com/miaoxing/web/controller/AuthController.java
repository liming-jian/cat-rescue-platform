package com.miaoxing.web.controller;

import com.miaoxing.business.entity.User;
import com.miaoxing.business.service.UserService;
import com.miaoxing.common.result.ApiResponse;
import com.miaoxing.web.dto.LoginRequest;
import com.miaoxing.web.dto.RegisterRequest;
import com.miaoxing.web.dto.ResetPasswordRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public AuthController(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ApiResponse<Void> register(@RequestBody RegisterRequest request) {
        if (request.getUsername() == null || request.getUsername().isBlank() || request.getPassword() == null || request.getPassword().isBlank()) {
            return ApiResponse.fail("不能为空");
        }
        boolean success = userService.register(request.getUsername(), request.getPassword());
        return success ? ApiResponse.success("注册成功", null) : ApiResponse.fail("用户名已存在");
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody LoginRequest request) {
        Map<String, Object> result = new HashMap<>();
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
            User user = userService.findByUsername(request.getUsername());
            result.put("success", true);
            Map<String, Object> userMap = new HashMap<>();
            userMap.put("username", user.getUsername());
            userMap.put("role", extractRole(authentication));
            result.put("user", userMap);
            return result;
        } catch (AuthenticationException e) {
            result.put("success", false);
            result.put("message", "账号或密码错误");
            return result;
        }
    }

    @PostMapping("/reset-password")
    public ApiResponse<Void> resetPassword(@RequestBody ResetPasswordRequest request) {
        if (request.getUsername() == null || request.getUsername().isBlank() || request.getNewPassword() == null || request.getNewPassword().isBlank()) {
            return ApiResponse.fail("信息不完整");
        }
        boolean success = userService.resetPassword(request.getUsername(), request.getNewPassword());
        return success ? ApiResponse.success("密码重置成功", null) : ApiResponse.fail("用户名不存在，请先注册");
    }

    private String extractRole(Authentication authentication) {
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            String role = authority.getAuthority();
            if (role.startsWith("ROLE_")) {
                return role.substring(5).toLowerCase();
            }
        }
        return "user";
    }
}
