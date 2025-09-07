package com.lldproject.bookmyshow.controller;

import com.lldproject.bookmyshow.dto.CreateShowRequestDto;
import com.lldproject.bookmyshow.dto.CreateShowResponseDto;
import com.lldproject.bookmyshow.dto.ResponseStatus;
import com.lldproject.bookmyshow.exceptions.*;
import com.lldproject.bookmyshow.repository.ScreenRepository;
import com.lldproject.bookmyshow.repository.UserRepository;
import com.lldproject.bookmyshow.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ShowController {
    private final ShowService showService;

    @Autowired
    public ShowController(ShowService showService) {
        this.showService = showService;
    }

    public CreateShowResponseDto createShow(CreateShowRequestDto createShowRequestDto){
        CreateShowResponseDto responseDto = new CreateShowResponseDto();
        try{
            long show_id = showService.createShow(createShowRequestDto.getCreated_by(), createShowRequestDto.getMovie_id(), createShowRequestDto.getScreen_id(), createShowRequestDto.getStart_time(), createShowRequestDto.getEnd_time(), createShowRequestDto.getLanguage(), createShowRequestDto.getSupported_feature(), createShowRequestDto.getSeatPricing());
            responseDto.setShow_id(show_id);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
            responseDto.setMessage("Show created successfully");
        }
        catch(UserNotFoundException| UnAuthorizedAccessException| ScreenNotFoundException| MovieNotFoundException| FeatureNotSupportedException| InvalidDateException| LanguageNotSupportedException e){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
            responseDto.setMessage(e.getMessage());
        }
        return responseDto;
    }
}
