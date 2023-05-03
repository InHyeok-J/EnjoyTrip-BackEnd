package com.enjoytrip.auth.entity;


import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class AuthHistory {

    private Long id;
    private String email;
    private String authCode;
    private boolean isAuth;
    private LocalDateTime sendDate;

    public static AuthHistory unAuthHistory(String email, String code){
        return AuthHistory.builder()
            .authCode(code)
            .email(email)
            .sendDate(LocalDateTime.now())
            .build();
    }

    public boolean checkAuthCode(String code){
        if(this.authCode.equals(code)){
            isAuth = true;
        }
        return isAuth;
    }

    public boolean checkTimeLimit(LocalDateTime currentTime){
        final int  TIME_LIMIT = 5;
        LocalDateTime timeLimit = sendDate.plusMinutes(TIME_LIMIT);
        return currentTime.isBefore(timeLimit);
    }
}
