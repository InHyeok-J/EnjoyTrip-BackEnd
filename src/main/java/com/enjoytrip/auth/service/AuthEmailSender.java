package com.enjoytrip.auth.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class AuthEmailSender implements AuthEmailService{


    @Override
    public void sendEmail(String code, String email) {
        log.info("send Email = code => " + code + " EMAIL = > " + email);
    }
}
