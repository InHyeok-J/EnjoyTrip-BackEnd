package com.enjoytrip.user.controller;


import com.enjoytrip.global.dto.SessionUser;
import com.enjoytrip.global.response.JsonResponse;
import com.enjoytrip.user.controller.dto.ModifyUserProfileRequest;
import com.enjoytrip.user.controller.dto.SignUpRequest;
import com.enjoytrip.user.controller.dto.UserResponse;
import com.enjoytrip.user.entity.User;
import com.enjoytrip.user.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
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
    public ResponseEntity<?> getLoginUserInfo(@AuthenticationPrincipal SessionUser sessionUser) {
        User userInfo = userService.getUserInfo(sessionUser.getId());
        return JsonResponse.okWithData(HttpStatus.OK, "유저 정보 조회성공", UserResponse.from(userInfo));
    }

    @PatchMapping("/user")
    public ResponseEntity<?> modefiyProfile(@ModelAttribute ModifyUserProfileRequest request,
        @AuthenticationPrincipal SessionUser sessionUser) {
        User user = userService.modifyProfile(request, sessionUser.getId());
        return JsonResponse.okWithData(HttpStatus.OK, "수정 성공", UserResponse.from(user));
    }

    @DeleteMapping("/user/signout")
    public ResponseEntity<?> signOut(@AuthenticationPrincipal SessionUser sessionUser,
        HttpServletRequest request) {
        userService.signOut(sessionUser.getId());
        HttpSession session = request.getSession();
        session.invalidate();
        return JsonResponse.ok(HttpStatus.OK, "회원 탈퇴 성공!");
    }
}
