package com.enjoytrip.auth.service.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Getter
public class OAuthDetail {

    private Long id;
    private String email;
    private String profileImage;

    @Builder
    public OAuthDetail(Long id, String email, String profileImage) {
        this.id = id;
        this.email = email;
        this.profileImage = profileImage;
    }

    @Override
    public String toString() {
        return "OAuthDetail{" +
            "id='" + id + '\'' +
            ", email='" + email + '\'' +
            ", profileImage='" + profileImage + '\'' +
            '}';
    }
}
