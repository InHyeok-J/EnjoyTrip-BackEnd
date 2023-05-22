package com.enjoytrip.global.config;

import com.enjoytrip.global.config.security.SessionAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsUtils;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final SessionAuthenticationEntryPoint sessionAuthenticationEntryPoint;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //csrf disable.
        http.csrf().disable();

        http
            .cors()
            .and()
            .authorizeRequests()
            .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
            .antMatchers("/user").authenticated()
            .antMatchers("/user/signout", HttpMethod.DELETE.name()).authenticated()
            .antMatchers(HttpMethod.POST, "/attractions/*/reviews/**").authenticated()
            .anyRequest().permitAll();
        http.formLogin()
            .disable(); // 스프링 시큐리티 기본 form 로그인을 disable 한다.

        http.exceptionHandling()
            .authenticationEntryPoint(sessionAuthenticationEntryPoint);
        return http.build();
    }

}
