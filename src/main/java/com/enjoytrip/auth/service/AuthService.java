package com.enjoytrip.auth.service;

public interface AuthService {

    void sendAuthMail(String email);
    void checkEmailAuth(String email, String code);
}
