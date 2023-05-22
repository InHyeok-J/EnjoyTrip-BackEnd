package com.enjoytrip.auth.service.dto;

import com.enjoytrip.auth.service.vo.Token;
import com.enjoytrip.user.entity.Provider;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class OAuthLoginDto {

    private Boolean isNewUser;
    private Token token;
    private OAuthLoginDto(Boolean isNewUser, Token token) {
        this.isNewUser = isNewUser;
        this.token = token;
    }

    public static OAuthLoginDto requireSignUpUser(Token token) {
        return new OAuthLoginDto(true, token);
    }

    public static OAuthLoginDto isLogin() {
        return new OAuthLoginDto(false, null);
    }
}
