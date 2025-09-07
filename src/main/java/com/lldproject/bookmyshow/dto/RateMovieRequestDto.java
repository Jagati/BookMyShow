package com.lldproject.bookmyshow.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RateMovieRequestDto {
    private long user_id;
    private long movie_id;
    private int rating;
}
