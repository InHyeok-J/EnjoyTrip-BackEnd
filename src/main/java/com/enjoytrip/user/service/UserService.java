package com.enjoytrip.user.service;

import com.enjoytrip.user.controller.dto.ModifyUserProfileRequest;
import com.enjoytrip.user.controller.dto.SignUpRequest;
import com.enjoytrip.user.entity.User;

public interface UserService {

    void signUp(SignUpRequest requestDto);

    User getUserInfo(Long userId);

    User modifyProfile(ModifyUserProfileRequest requestDto,Long userId);

    void signOut(Long id);

    User selectNicknameProfileByCourseId(Long id);
}
