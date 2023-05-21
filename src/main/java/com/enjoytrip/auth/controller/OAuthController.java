package com.enjoytrip.auth.controller;


import com.enjoytrip.auth.controller.dto.OAuthLoginRequest;
import com.enjoytrip.auth.controller.dto.OAuthSignupRequest;
import com.enjoytrip.auth.service.OAuthService;
import com.enjoytrip.auth.service.dto.OAuthLoginDto;
import com.enjoytrip.auth.service.vo.Token;
import com.enjoytrip.global.response.JsonResponse;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
//@CrossOrigin(origins = {"http://localhost:8080/"}, allowCredentials = t)
public class OAuthController {

    private final OAuthService oAuthService;

    @PostMapping("/oauth/kakao")
    public ResponseEntity<?> oauthKakaoLogin(@RequestBody OAuthLoginRequest requestDto,
        HttpServletResponse response) {
        OAuthLoginDto result = oAuthService.kakaoLogin(requestDto);

        if (result.getIsNewUser().equals(true)) {
            response.addCookie(result.getToken().generateAccessTokenCookie());
//            Map<String, String> headers = new HashMap<>();
//            headers.put("Set-Cookie", result.getToken().generateAccessTokenCookie().toString());
//            return JsonResponse.okWithHeadersBody(HttpStatus.OK, "회원가입이 필요합니다",
//                headers, result);
        }
        return JsonResponse.okWithData(HttpStatus.OK, "OAuth 로그인 처리", result);
    }

    @PostMapping("/oauth/signup")
    public ResponseEntity<?> oauthSignup(@RequestBody OAuthSignupRequest requestDto,
        @CookieValue(value = "accessToken", required = false) String accessToken) {
        if (accessToken == null) {
            return JsonResponse.fail("가입 정보가 없습니다.", 400);
        }
        oAuthService.oauthSignUp(requestDto, accessToken);
        return JsonResponse.ok(HttpStatus.OK, "");
    }
}
