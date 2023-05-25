package com.enjoytrip.user.service;

import com.enjoytrip.user.controller.dto.ModifyUserProfileRequest;
import com.enjoytrip.user.controller.dto.SignUpRequest;
import com.enjoytrip.user.entity.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    void signUp(SignUpRequest requestDto);

    User getUserInfo(Long userId);

    User modifyProfile(ModifyUserProfileRequest requestDto, MultipartFile file,Long userId);

    void signOut(Long id);

    User selectNicknameProfileByCourseId(Long id);
}
