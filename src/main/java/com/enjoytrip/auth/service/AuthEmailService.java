package com.enjoytrip.auth.service;

public interface AuthEmailService {

    void sendEmail(String code, String email);
}
