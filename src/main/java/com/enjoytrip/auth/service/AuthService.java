package com.enjoytrip.auth.service;

import com.enjoytrip.auth.controller.dto.LoginRequest;

public interface AuthService {

    void sendAuthMail(String email);
    void checkEmailAuth(String email, String code);
    void login(LoginRequest request);
}
