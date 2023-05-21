package com.enjoytrip.auth.service;


import com.enjoytrip.auth.controller.dto.OAuthLoginRequest;
import com.enjoytrip.auth.controller.dto.OAuthSignupRequest;
import com.enjoytrip.auth.service.dto.OAuthDetail;
import com.enjoytrip.auth.service.dto.OAuthLoginDto;
import com.enjoytrip.auth.service.vo.Token;
import com.enjoytrip.global.exception.BusinessException;
import com.enjoytrip.user.dao.UserMapper;
import com.enjoytrip.user.entity.Provider;
import com.enjoytrip.user.entity.Role;
import com.enjoytrip.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OAuthServiceImpl implements OAuthService {

    private final KakaoOAuthProvider kakaoOAuthProvider;
    private final UserMapper userMapper;
    private final AuthService authService;
    @Override
    public OAuthLoginDto kakaoLogin(OAuthLoginRequest request) {
        Token token = kakaoOAuthProvider.getToken(request.getCode());
        OAuthDetail userInfo = kakaoOAuthProvider.getOAuthUserInfo(token.getAccessToken());

        User user = userMapper.selectByEmail(userInfo.getEmail());

        if (isAlreadyUserByOtherProvider(user)) {
            throw new BusinessException("이미 다른 방법으로 가입된 유저입니다", 409);
        }

        if (isRequireSignUp(user)) {
            return OAuthLoginDto.requireSignUpUser(token);
        }

        authService.setSessionUserInfo(user);
        return OAuthLoginDto.isLogin();
    }

    private boolean isRequireSignUp(User user) {
        return user == null;
    }

    private boolean isAlreadyUserByOtherProvider(User user) {
        return user != null && !user.getProvider().equals(Provider.KAKAO);
    }


    @Transactional
    @Override
    public void oauthSignUp(OAuthSignupRequest request, String token) {
        OAuthDetail userInfo = kakaoOAuthProvider.getOAuthUserInfo(token);
        User user = User.builder()
            .nickname(request.getNickname())
            .provider(request.getProvider())
            .email(userInfo.getEmail())
            .snsId(userInfo.getId())
            .role(Role.USER)
            .profileImg(userInfo.getProfileImage())
            .build();
        userMapper.insert(user);
        System.out.println("user = " + user);
        authService.setSessionUserInfo(user);
    }

}
