package com.lldproject.bookmyshow.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetAverageRatingResponseDto {
    private double averageRating;
    private ResponseStatus responseStatus;
    private String message;
}
