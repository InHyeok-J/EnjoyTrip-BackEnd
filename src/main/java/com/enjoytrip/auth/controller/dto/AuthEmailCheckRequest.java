package com.enjoytrip.auth.controller.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthEmailCheckRequest {

    @Email
    private String email;

    @NotBlank
    private String authCode;

}
