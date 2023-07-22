package com.productapi.exception;

import com.dto.ErrorDTO;
import com.exception.CategoryNotFoundException;
import com.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;
import java.util.List;

@ControllerAdvice(basePackages = "com.productapi.controller")
public class ErrorHandlerController {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    public ErrorDTO hadlerProductNotFound(){
        return new ErrorDTO()
                .setStatus(HttpStatus.NOT_FOUND.value())
                .setMessage("Product not found")
                .setTimestamp(new Date());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CategoryNotFoundException.class)
    public ErrorDTO handlerCaterogyNotFound(){
        return new ErrorDTO()
                .setStatus(HttpStatus.NOT_FOUND.value())
                .setMessage("Category not found")
                .setTimestamp(new Date());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorDTO processValidationError(MethodArgumentNotValidException ex){

        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        StringBuilder sb = new StringBuilder("Invalid value to the following fields: ");
        for (FieldError fieldError : fieldErrors) {
            sb.append(" ");
            sb.append(fieldError.getField());
        }

        return new ErrorDTO()
                .setStatus(HttpStatus.BAD_REQUEST.value())
                .setMessage(sb.toString())
                .setTimestamp(new Date());
    }
}
