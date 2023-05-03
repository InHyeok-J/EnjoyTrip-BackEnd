package com.enjoytrip.auth.service;


import com.enjoytrip.auth.dao.AuthHistoryMapper;
import com.enjoytrip.auth.entity.AuthHistory;
import com.enjoytrip.global.exception.BusinessException;
import com.enjoytrip.user.dao.UserMapper;
import com.enjoytrip.user.entity.User;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthEmailService authEmailService;
    private final UserMapper userMapper;
    private final AuthHistoryMapper authHistoryMapper;

    @Transactional
    public void sendAuthMail(String email) {
        User user = userMapper.selectByEmail(email);
        if(alreadyUseEmail(user)){
            throw new BusinessException("이미 사용중인 이메일입니다", 409);
        }

        String authCode = generateAuthCode();

        AuthHistory prevAuth = AuthHistory.createPrevAuth(email, authCode);
        prevAuth.setAuthCode(authCode);
        authHistoryMapper.insert(prevAuth);
        authEmailService.sendEmail(authCode,email);
    }

    @Transactional
    public void checkEmailAuth(String email, String code){
        AuthHistory history = authHistoryMapper.selectByEmailLastAuth(email);
        if(history == null){
            throw new BusinessException("인증 요청된 이력이 없습니다.",404);
        }

        if(!history.checkTimeLimit(LocalDateTime.now())){
            throw  new BusinessException("제한 시간을 초과했습니다", 409);
        }

        if(!history.checkAuthCode(code)){
            throw new BusinessException("인증 코드가 일치하지 않습니다.",409);
        }

        authHistoryMapper.updateAuthStatus(history);
    }

    private boolean alreadyUseEmail(User user){
        return user != null;
    }

    private String generateAuthCode(){
        return UUID.randomUUID().toString();
    }

}
