package com.enjoytrip.user.controller.dto;

import com.enjoytrip.user.entity.Provider;
import com.enjoytrip.user.entity.User;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserResponse {

    private Long id;
    private String nickname;
    private String email;
    private String profileImg;
    private Provider provider;
    private LocalDateTime createdAt;

    @Builder
    public UserResponse(Long id, String nickname, String email, String profileImg,
        Provider provider,
        LocalDateTime createdAt) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.profileImg = profileImg;
        this.provider = provider;
        this.createdAt = createdAt;
    }

    public static UserResponse from(User user) {
        return UserResponse.builder()
            .id(user.getId())
            .nickname(user.getNickname())
            .email(user.getEmail())
            .profileImg(user.getProfileImg())
            .provider(user.getProvider())
            .createdAt(user.getCreatedAt())
            .build();
    }
}
