package com.lldproject.bookmyshow.dto;

import com.lldproject.bookmyshow.model.UserRating;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RateMovieResponseDto {
    private ResponseStatus responseStatus;
    private String message;
    private UserRating userRating;
}
