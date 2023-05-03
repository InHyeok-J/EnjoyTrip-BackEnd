package com.enjoytrip.auth.controller;


import com.enjoytrip.auth.controller.dto.AuthEmailCheckRequest;
import com.enjoytrip.auth.controller.dto.AuthEmailSendRequest;
import com.enjoytrip.auth.controller.dto.LoginRequest;
import com.enjoytrip.auth.service.AuthService;
import com.enjoytrip.global.response.JsonResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/auth/history")
    public ResponseEntity<?> sendAuthEmail(@Valid @RequestBody AuthEmailSendRequest requestDto) {
        authService.sendAuthMail(requestDto.getEmail());
        return JsonResponse.ok(HttpStatus.OK, "메일 전송 성공");
    }

    @PostMapping("/auth/history/check")
    public ResponseEntity<?> sendAuthEmailCheck(
        @Valid @RequestBody AuthEmailCheckRequest requestDto) {
        authService.checkEmailAuth(requestDto.getEmail(), requestDto.getAuthCode());
        return JsonResponse.ok(HttpStatus.OK, "인증 확인 성공");
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest requestDto) {
        authService.login(requestDto);
        return JsonResponse.ok(HttpStatus.OK, "로그인 성공");
    }

    @GetMapping("/auth/logout")
    public ResponseEntity<?> logout(HttpSession session, @AuthenticationPrincipal Object a) {
        session.invalidate();
        return JsonResponse.ok(HttpStatus.OK, "로그아웃 성공");
    }

}
