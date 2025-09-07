package com.lldproject.bookmyshow.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateShowResponseDto {
    private long show_id;
    private String message;
    private ResponseStatus responseStatus;
}
