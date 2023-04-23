package com.chatroom.chatroom.rest.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.chatroom.chatroom.exception.user.UserBusinessException;
import com.chatroom.chatroom.rest.exception.ErrorDto;

@RestControllerAdvice
public class UserExceptionHandler {
    
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserBusinessException.class)
    public ErrorDto userBusinessExceitionHandler(UserBusinessException exception) {
        return ErrorDto.forException(exception);
    }

}
