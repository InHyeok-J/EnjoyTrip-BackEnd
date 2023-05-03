package com.enjoytrip.user.controller;


import com.enjoytrip.global.response.JsonResponse;
import com.enjoytrip.user.controller.dto.SignUpRequest;
import com.enjoytrip.user.service.UserService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
}
