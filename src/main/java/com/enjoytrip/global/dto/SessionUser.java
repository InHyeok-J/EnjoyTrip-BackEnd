package com.enjoytrip.global.dto;


import com.enjoytrip.user.entity.Role;
import com.enjoytrip.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SessionUser {

    private Long id;
    private String email;
    private String nickname;
    private Role role;

    public static SessionUser from(User user) {
        return new SessionUser(user.getId(), user.getEmail(), user.getNickname(), user.getRole());
    }

}
