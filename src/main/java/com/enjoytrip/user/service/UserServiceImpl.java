package com.enjoytrip.user.service;

import com.enjoytrip.auth.dao.AuthHistoryMapper;
import com.enjoytrip.auth.entity.AuthHistory;
import com.enjoytrip.auth.service.KakaoOAuthProvider;
import com.enjoytrip.global.common.S3Uploader;
import com.enjoytrip.global.exception.BusinessException;
import com.enjoytrip.user.controller.dto.ModifyUserProfileRequest;
import com.enjoytrip.user.controller.dto.SignUpRequest;
import com.enjoytrip.user.dao.UserMapper;
import com.enjoytrip.user.entity.Provider;
import com.enjoytrip.user.entity.User;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final AuthHistoryMapper authHistoryMapper;
    private final PasswordEncoder passwordEncoder;

    private final S3Uploader s3Uploader;
    private final KakaoOAuthProvider kakaoOAuthProvider;


    @Transactional
    @Override
    public void signUp(SignUpRequest requestDto) {
        User alreadyUsingEmailUser = userMapper.selectByEmail(requestDto.getEmail());

        if (alreadyUsingEmailUser != null) {
            throw new BusinessException("이미 사용중인 이메일입니다", 409);
        }

        AuthHistory authHistory = authHistoryMapper.selectByEmailLastAuth(requestDto.getEmail());

        if (authHistory == null || !authHistory.isAuth()) {
            throw new BusinessException("이메일 인증이 필요합니다", 409);
        }

        if (!authHistory.checkTimeLimit(LocalDateTime.now())) {
            throw new BusinessException("만료된 인증 정보입니다.", 409);
        }

        User user = requestDto.toEntity();

        user.updatePassword(passwordEncoder.encode(user.getPassword()));

        userMapper.insert(user);
    }


    @Override
    public User getUserInfo(Long userId) {
        User user = userMapper.selectById(userId);
        if (user == null) {
            throw new BusinessException("없는 유저입니다", 404);
        }
        return user;
    }

    @Transactional
    @Override
    public User modifyProfile(ModifyUserProfileRequest requestDto, Long userId) {
        User user = userMapper.selectById(userId);
        if (requestDto.getImage().isPresent()) {
            String url = s3Uploader.uploadFile(requestDto.getImage().get());
            user.setProfileImg(url);
        }
        user.setNickname(requestDto.getNickname());
        int result = userMapper.updateProfile(user);
        if (result == 0) {
            throw new BusinessException("업데이트 실패", 500);
        }
        return user;
    }

    @Transactional
    @Override
    public void signOut(Long id) {
        User user = userMapper.selectById(id);

        if (user.getProvider().equals(Provider.KAKAO)) {
            kakaoOAuthProvider.signOutKakao(user.getSnsId());
        }

        userMapper.deleteById(id);
    }
}
