package com.enjoytrip.auth.service;


import com.enjoytrip.auth.controller.dto.LoginRequest;
import com.enjoytrip.auth.dao.AuthHistoryMapper;
import com.enjoytrip.auth.entity.AuthHistory;
import com.enjoytrip.global.dto.SessionUser;
import com.enjoytrip.global.exception.BusinessException;
import com.enjoytrip.user.dao.UserMapper;
import com.enjoytrip.user.entity.User;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthEmailService authEmailService;
    private final UserMapper userMapper;
    private final AuthHistoryMapper authHistoryMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void sendAuthMail(String email) {
        User user = userMapper.selectByEmail(email);
        if (alreadyUseEmail(user)) {
            throw new BusinessException("이미 사용중인 이메일입니다", 409);
        }

        String authCode = generateAuthCode();

        AuthHistory prevAuth = AuthHistory.unAuthHistory(email, authCode);
        prevAuth.setAuthCode(authCode);
        authHistoryMapper.insert(prevAuth);
        authEmailService.sendEmail(authCode, email);
    }

    @Override
    @Transactional
    public void checkEmailAuth(String email, String code) {
        AuthHistory history = authHistoryMapper.selectByEmailLastAuth(email);
        if (history == null) {
            throw new BusinessException("인증 요청된 이력이 없습니다.", 404);
        }

        if (!history.checkTimeLimit(LocalDateTime.now())) {
            throw new BusinessException("제한 시간을 초과했습니다", 409);
        }

        if (!history.checkAuthCode(code)) {
            throw new BusinessException("인증 코드가 일치하지 않습니다.", 409);
        }

        authHistoryMapper.updateAuthStatus(history);
    }

    @Override
    public void login(LoginRequest request) {
        User user = userMapper.selectByEmail(request.getEmail());
        if (user == null) {
            throw new BusinessException("없는 유저입니다", 404);
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BusinessException("정보가 일치하지 않습니다", 409);
        }

        setSessionUserInfo(user);
    }

    private void setSessionUserInfo(User user) {
        SecurityContext emptyContext = SecurityContextHolder.getContext();
        Collection<? extends GrantedAuthority> authorities = new ArrayList<>(
            Collections.singleton(new SimpleGrantedAuthority(user.getRole().name())));
        emptyContext.setAuthentication(
            new UsernamePasswordAuthenticationToken(SessionUser.from(user), null, authorities));
    }

    private boolean alreadyUseEmail(User user) {
        return user != null;
    }

    private String generateAuthCode() {
        return UUID.randomUUID().toString();
    }

}
