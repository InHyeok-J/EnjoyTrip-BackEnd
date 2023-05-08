package com.enjoytrip.user.service;

import com.enjoytrip.user.controller.dto.SignUpRequest;

public interface UserService {

    void signUp(SignUpRequest requestDto);
}
