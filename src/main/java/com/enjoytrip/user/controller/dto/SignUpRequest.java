package com.enjoytrip.user.controller.dto;

import com.enjoytrip.user.entity.Provider;
import com.enjoytrip.user.entity.Role;
import com.enjoytrip.user.entity.User;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {

    @Email
    private String email;

    @NotBlank
    private String nickname;

    @NotBlank
    private String password;

    public User toEntity(){
        return User.builder()
            .nickname(nickname)
            .email(email)
            .password(password)
            .provider(Provider.LOCAL)
            .role(Role.USER)
            .build();
    }
}
