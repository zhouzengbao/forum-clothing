package com.forum.clothing.config;

import com.forum.clothing.util.result.Result;
import com.forum.clothing.util.result.Results;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class BaseExceptionHandler {
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<?> handle(MethodArgumentNotValidException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        return Results.failure(fieldError.getDefaultMessage());
    }
}
