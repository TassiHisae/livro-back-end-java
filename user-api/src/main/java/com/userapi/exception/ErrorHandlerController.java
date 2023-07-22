package com.userapi.exception;

import com.dto.ErrorDTO;
import com.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;

@ControllerAdvice(basePackages = "com.userapi.controller")
public class ErrorHandlerController {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ErrorDTO hadlerUserNotFound(){
        return new ErrorDTO()
                .setStatus(HttpStatus.NOT_FOUND.value())
                .setMessage("User not found")
                .setTimestamp(new Date());
    }
}
