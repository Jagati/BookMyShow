package com.lldproject.bookmyshow.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserSignUpRequestDto {
    private String username;
    private String email;
    private String password;
}
