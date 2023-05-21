package com.enjoytrip.auth.controller.dto;

import com.enjoytrip.user.entity.Provider;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@NoArgsConstructor
@Setter
@ToString
public class OAuthSignupRequest {

    @NotBlank
    private String nickname;

    @NotBlank
    private Provider provider;
}
