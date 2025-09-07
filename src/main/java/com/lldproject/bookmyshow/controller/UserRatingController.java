package com.lldproject.bookmyshow.controller;

import com.lldproject.bookmyshow.dto.*;
import com.lldproject.bookmyshow.exceptions.MovieNotFoundException;
import com.lldproject.bookmyshow.exceptions.UserNotFoundException;
import com.lldproject.bookmyshow.model.UserRating;
import com.lldproject.bookmyshow.service.UserRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserRatingController {
    private final UserRatingService userRatingService;
    @Autowired
    public UserRatingController(UserRatingService userRatingService) {
        this.userRatingService = userRatingService;
    }

    public RateMovieResponseDto rateMovie(RateMovieRequestDto rateMovieRequestDto) {
        RateMovieResponseDto responseDto = new RateMovieResponseDto();
        try{
            UserRating userRating = userRatingService.rateMovie(rateMovieRequestDto.getUser_id(), rateMovieRequestDto.getMovie_id(), rateMovieRequestDto.getRating());
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
            responseDto.setMessage("Rated successfully");
            responseDto.setUserRating(userRating);
        }
        catch (UserNotFoundException | MovieNotFoundException e){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
            responseDto.setMessage(e.getMessage());
        }
        return responseDto;
    }

    public GetAverageRatingResponseDto getAverageRating(GetAverageRatingRequestDto getAverageRatingRequestDto){
        GetAverageRatingResponseDto responseDto = new GetAverageRatingResponseDto();
        try{
            double averageRating = userRatingService.getAverageRating(getAverageRatingRequestDto.getMovie_id());
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
            responseDto.setAverageRating(averageRating);
            responseDto.setMessage("Average rating fetched successfully");
        }
        catch(MovieNotFoundException e){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
            responseDto.setMessage(e.getMessage());
        }
        return responseDto;
    }
}
