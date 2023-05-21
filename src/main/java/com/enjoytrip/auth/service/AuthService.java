package com.enjoytrip.auth.service;

import com.enjoytrip.auth.controller.dto.LoginRequest;
import com.enjoytrip.user.entity.User;

public interface AuthService {

    void sendAuthMail(String email);
    void checkEmailAuth(String email, String code);
    void login(LoginRequest request);

    void setSessionUserInfo(User user);
}
