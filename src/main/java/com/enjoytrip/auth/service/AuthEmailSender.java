package com.enjoytrip.auth.service;


import com.enjoytrip.global.exception.BusinessException;
import javax.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class AuthEmailSender implements AuthEmailService {

    private final JavaMailSender javaMailSender;

    @Override
    public void sendEmail(String code, String email) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false,
                "UTF-8");
            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setSubject("[EnjoyTrip] 이메일 인증 코드입니다.");
            mimeMessageHelper.setText("인증 코드 : "+code);
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            log.warn("이메일 전송 실패");
            throw new BusinessException("이메일 전송 실패!!", 500);
        }
    }
}
