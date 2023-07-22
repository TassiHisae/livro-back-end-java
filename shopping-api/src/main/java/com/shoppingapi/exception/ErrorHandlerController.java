package com.shoppingapi.exception;

import com.dto.ErrorDTO;
import com.exception.ProductNotFoundException;
import com.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;

@ControllerAdvice("com.controller")
public class ErrorHandlerController {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ErrorDTO handlerUserNotFound(){
        return new ErrorDTO()
                .setStatus(HttpStatus.NOT_FOUND.value())
                .setMessage("User not found")
                .setTimestamp(new Date());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    public ErrorDTO handlerProductNotFound(){
        return new ErrorDTO()
                .setStatus(HttpStatus.NOT_FOUND.value())
                .setMessage("Product not found")
                .setTimestamp(new Date());
    }
}
