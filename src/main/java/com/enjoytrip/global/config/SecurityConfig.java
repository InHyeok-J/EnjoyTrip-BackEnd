package com.enjoytrip.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //csrf disable.
        http.csrf().disable();

        http.formLogin()
            .disable(); // 스프링 시큐리티 기본 form 로그인을 disable 한다.

        return http.build();
    }

}
