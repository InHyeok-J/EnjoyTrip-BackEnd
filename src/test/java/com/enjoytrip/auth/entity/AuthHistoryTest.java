package com.enjoytrip.auth.entity;


import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AuthHistoryTest {

    @Test
    @DisplayName("제한 시간(5분) 이후 요청이 오면 false를 리턴한다")
    void testTimeLimitFail(){
        LocalDateTime sendDateTime = LocalDateTime.of(2023,5,5,5,30);//5시 30분
        LocalDateTime checkTime = LocalDateTime.of(2023,5,5,5,36);//5시 32분
        AuthHistory authHistory = AuthHistory.builder()
            .sendDate(sendDateTime)
            .build();

        boolean result = authHistory.checkTimeLimit(checkTime);
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("제한 시간(5분) 이전 요청이 오면 true를 리턴한다")
    void testTimeLimitSuccess(){
        LocalDateTime sendDateTime = LocalDateTime.of(2023,5,5,5,30);//5시 30분
        LocalDateTime checkTime = LocalDateTime.of(2023,5,5,5,34);//5시 32분
        AuthHistory authHistory = AuthHistory.builder()
            .sendDate(sendDateTime)
            .build();

        boolean result = authHistory.checkTimeLimit(checkTime);
        assertThat(result).isTrue();
    }
}