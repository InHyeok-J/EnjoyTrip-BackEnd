package com.enjoytrip.auth.service;

import com.enjoytrip.auth.controller.dto.OAuthLoginRequest;
import com.enjoytrip.auth.controller.dto.OAuthSignupRequest;
import com.enjoytrip.auth.service.dto.OAuthLoginDto;

public interface OAuthService {

    OAuthLoginDto kakaoLogin(OAuthLoginRequest request);
    void oauthSignUp(OAuthSignupRequest request, String token);
}
