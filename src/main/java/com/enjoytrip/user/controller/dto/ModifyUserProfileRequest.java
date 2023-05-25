package com.enjoytrip.user.controller.dto;

import java.util.Optional;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ModifyUserProfileRequest {

    private String nickname;

    public ModifyUserProfileRequest(String nickname) {
        this.nickname = nickname;
    }
}
