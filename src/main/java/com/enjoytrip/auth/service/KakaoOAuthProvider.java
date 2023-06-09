package com.enjoytrip.auth.service;

import com.enjoytrip.auth.service.dto.KakaoTokenProperty;
import com.enjoytrip.auth.service.dto.OAuthDetail;
import com.enjoytrip.auth.service.vo.Token;
import com.enjoytrip.global.exception.BusinessException;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class KakaoOAuthProvider {

    private final WebClient kakaoAuthWebClient;
    private final WebClient kakaoApiWebClient;
    private final String AUTH_HEADER = "Authorization";

    @Value("${oauth.kakao.client_key}")
    private String KAKAO_CLIENT_KEY;

    @Value("${oauth.kakao.redirect}")
    private String KAKAO_REDIRECT_URI;

    @Value("${oauth.kakao.admin_key}")
    private String KAKAO_ADMIN_KEY;

    public Token getToken(String authCode) {
        String uri = oauthAccessTokenUri(authCode);
        try {
            KakaoTokenProperty result = kakaoAuthWebClient.post()
                .uri(uri)
                .header("Content-type", "application/x-www-form-urlencoded;charset=utf-8")
                .retrieve()
                .bodyToMono(KakaoTokenProperty.class)
                .block();
            return new Token(result.getAccessToken());
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("카카오 로그인 서버에러!", 500);
        }
    }


    public OAuthDetail getOAuthUserInfo(String token) {
        final String uri = "/v2/user/me";

        try {
            Map result = kakaoApiWebClient.post()
                .uri(uri)
                .header(AUTH_HEADER, "Bearer " + token)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

            Long id = (Long) result.get("id");
            Map<String, Object> kakaoAccount = (Map<String, Object>) result.get("kakao_account");
            Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
            String profileImageUrl = (String) profile.get("profile_image_url");
            String email = (String) kakaoAccount.get("email");

            return new OAuthDetail(id, email, profileImageUrl);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("카카오 API 서버 에러!", 500);
        }
    }

    public void signOutKakao(Long snsId) {
        String UN_LINK_URI = unlinkUri(snsId);
        try {
            Map result = kakaoApiWebClient.post()
                .uri(UN_LINK_URI)
                .header(AUTH_HEADER, "KakaoAK " + KAKAO_ADMIN_KEY)
                .header("Content-type", "application/x-www-form-urlencoded")
                .retrieve()
                .bodyToMono(Map.class)
                .block();
            System.out.println("result! " + result);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("카카오 인증 서버에러!", 500);
        }
    }

    private String oauthAccessTokenUri(String code) {
        StringBuilder sb = new StringBuilder();
        final String URI_PREFIX = "/oauth/token";
        sb.append(URI_PREFIX)
            .append("?grant_type=authorization_code")
            .append("&redirect_uri=").append(KAKAO_REDIRECT_URI)
            .append("&client_id=").append(KAKAO_CLIENT_KEY)
            .append("&code=").append(code);
        return sb.toString();
    }

    private String unlinkUri(Long snsId) {
        StringBuilder sb = new StringBuilder();
        final String URI_PREFIX = "/v1/user/unlink";
        sb.append(URI_PREFIX)
            .append("?target_id_type=user_id")
            .append("&target_id=").append(snsId);
        return sb.toString();
    }
}
