package com.lldproject.bookmyshow.controller;

import com.lldproject.bookmyshow.dto.*;
import com.lldproject.bookmyshow.model.User;
import com.lldproject.bookmyshow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public UserSignUpResponseDto signUpUser(UserSignUpRequestDto userSignUpRequestDto) {
        UserSignUpResponseDto responseDto = new UserSignUpResponseDto();
        try{
            User user = userService.signUpUser(userSignUpRequestDto.getUsername(), userSignUpRequestDto.getEmail(), userSignUpRequestDto.getPassword());
            responseDto.setUserId(user.getId());
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
            responseDto.setMessage("User signed up successfully");
        }
        catch(Exception e){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
            responseDto.setMessage("User sign up failed. "+ e.getMessage());
        }
        return responseDto;
    }
    public UserLoginResponseDto loginUser(UserLoginRequestDto userLoginRequestDto){
        UserLoginResponseDto responseDto = new UserLoginResponseDto();
        try{
            if(userService.loginUser(userLoginRequestDto.getEmail(), userLoginRequestDto.getPassword())){
                responseDto.setResponseStatus(ResponseStatus.SUCCESS);
                responseDto.setMessage("User logged in successfully");
            }
            else{
                responseDto.setResponseStatus(ResponseStatus.FAILURE);
                responseDto.setMessage("Incorrect username or password.");
            }
        }
        catch(Exception e){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
            responseDto.setMessage("User login failed. "+ e.getMessage());
        }
        return responseDto;
    }
}
