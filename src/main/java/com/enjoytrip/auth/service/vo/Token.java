package com.enjoytrip.auth.service.vo;


import javax.servlet.http.Cookie;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Token {

    private String accessToken;

    public Token(String accessToken) {
        this.accessToken = accessToken;
    }

    public Cookie generateAccessTokenCookie() {
        Cookie cookie = new Cookie("accessToken", this.accessToken);
        cookie.setMaxAge(60 * 10);
        cookie.setHttpOnly(true);
        cookie.setPath("/oauth/signup");
        return cookie;
    }
}
