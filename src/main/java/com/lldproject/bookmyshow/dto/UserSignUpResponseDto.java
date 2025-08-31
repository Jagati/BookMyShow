package com.lldproject.bookmyshow.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignUpResponseDto {
    private String message;
    private ResponseStatus responseStatus;
    private Long userId;
}
