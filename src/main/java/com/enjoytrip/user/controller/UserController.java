package com.enjoytrip.user.controller;


import com.enjoytrip.global.dto.SessionUser;
import com.enjoytrip.global.exception.BusinessException;
import com.enjoytrip.global.response.JsonResponse;
import com.enjoytrip.user.controller.dto.SignUpRequest;
import com.enjoytrip.user.service.UserService;
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
public class UserController {

    private final UserService userService;

    @PostMapping("/user/signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpRequest requestDto) {
        userService.signUp(requestDto);
        return JsonResponse.ok(HttpStatus.CREATED, "회원가입 성공");
    }

    @GetMapping("/user")
    public ResponseEntity<?> getLoginUserInfo(@AuthenticationPrincipal SessionUser sessionUser){
        if(sessionUser == null){
            throw new BusinessException("로그인이 필요합니다",401);
        }
        return JsonResponse.okWithData(HttpStatus.OK,"유저 정보 조회성공",sessionUser);
    }

}
