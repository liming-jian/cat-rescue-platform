package com.miaoxing.security.auth;

import org.springframework.security.crypto.password.PasswordEncoder;

public class PlainTextCompatiblePasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword == null ? "" : rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        String raw = rawPassword == null ? "" : rawPassword.toString();
        String encoded = encodedPassword == null ? "" : encodedPassword;
        return raw.equals(encoded);
    }
}
